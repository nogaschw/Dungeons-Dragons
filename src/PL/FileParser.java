package PL;

import BL.*;
import BL.Tiles.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FileParser {

    public GameLevel parseLevel(String levelFile, int idx,MessageCallback messageCallback){
        TileFactory tl = new TileFactory();
        List<String> content = null;
        try {
            content = Files.readAllLines(Paths.get(levelFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
        int rows = content.size();
        int cols = content.get(0).length();
        Tile[][] tiles = new Tile[rows][cols];
        Player player = null;
        Input input = new InputClass();
        List<Enemy> enemies=new LinkedList<Enemy>();
        for (int i=0;i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                Position p = new Position(j, i);
                char tile = content.get(i).charAt(j);
                if (tile == '#')
                    tiles[i][j]=tl.produceWall(p);
                else if (tile == '.')
                    tiles[i][j]=tl.produceEmpty(p);
                else if (tile == '@') {
                    tiles[i][j]=player= tl.producePlayer(idx,p,messageCallback,input);
                }
                else {
                    Enemy e=tl.produceEnemy(tile,messageCallback,p);
                    tiles[i][j]=e;
                    enemies.add(e);
                }
            }
        }
        GameBoard board = new GameBoard(tiles);
        GameLevel m = new GameLevel(board,player,enemies);
        return m;
    }
}

