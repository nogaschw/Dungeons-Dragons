package PL;
import BL.GameBoard;
import BL.GameLevel;
import BL.MessageCallback;
import BL.Position;
import BL.Tiles.Enemy;
import BL.Tiles.Player;
import PL.FileParser;
import PL.TileFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to Dungeons and Dragons Game\n Select player:");
            TileFactory tileFactory = new TileFactory();
            tileFactory.PrintThePlayers();
            ;
            Scanner scanner = new Scanner(System.in);
            int indexPlayer = scanner.nextInt();
            while (indexPlayer > 6 | indexPlayer < 1) {
                System.out.println("The selected number is invalid, please choose number between 1-" + tileFactory.listPlayers().size() + "\n Select player:");//check if need tileFactory.listPlayers().size()-1
                indexPlayer = scanner.nextInt();
            }
            List<GameLevel> levels = new ArrayList<GameLevel>();
            MessageCallback messageCallback = new CLI();

            //Load the game
            List<String> levelFiles = Files.list(Paths.get(args[0])).sorted().map(Path::toString).collect(Collectors.toList());
            for (String levelPath : levelFiles) {
                FileParser fileParser = new FileParser();
                levels.add(fileParser.parseLevel(levelPath, indexPlayer - 1, messageCallback));
            }
            messageCallback.send(String.format("You have selected:\n %s ", levels.get(0).GetPlayer().getName()));
            boolean stop = false;
            for (int i = 0; i < levels.size() & (!stop); i++) {
                GameLevel level = levels.get(i);
                GameBoard gameBoard = level.GetGameBoard();
                GameManeger gameManeger = new GameManeger(gameBoard, level.GetEnemies(), level.GetPlayer(), messageCallback);
                while (!gameManeger.getdeadPlayer() && !gameManeger.getallEnemiesDead())
                    gameManeger.gameTick();
                if (gameManeger.getdeadPlayer())
                    stop = true;
                if (!stop & gameManeger.getallEnemiesDead()) {
                    if (levels.size() - 1 <= i) {
                        System.out.println("YOU WIN !");
                    }
                    else {
                        levels.get(i+1).GetPlayer().setLevel(level.GetPlayer().getLevel());
                        levels.get(i+1).GetPlayer().setExperience(level.GetPlayer().getExperience());
                        levels.get(i+1).GetPlayer().SetDefense(level.GetPlayer().getDefense());
                        levels.get(i+1).GetPlayer().SetAttack(level.GetPlayer().getAttack());
                        levels.get(i+1).GetPlayer().setHealth(level.GetPlayer().getHealth());
                    }
                }

            }
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\n" + e.getStackTrace());
        }
    }

}
