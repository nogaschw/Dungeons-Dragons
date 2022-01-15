package BL;

import BL.Tiles.Empty;
import BL.Tiles.Enemy;
import BL.Tiles.Player;
import BL.Tiles.Tile;
import PL.Input;

import java.util.LinkedList;
import java.util.List;

public class GameLevel {

        private GameBoard gameBoard;
        private Player player;
        private List<Enemy> enemies;
        private List<Enemy> removeFrom;


        public GameLevel(GameBoard gameBoard,Player player,List<Enemy> enemies)
        {
                this.gameBoard=gameBoard;
                this.enemies=enemies;
                this.player=player;
                this.removeFrom=new LinkedList<Enemy>();
        }
        public GameBoard GetGameBoard(){return gameBoard;}
        public List<Enemy> GetEnemies(){return enemies;}
        public Player GetPlayer(){return player;}

        public GameBoard round() {
                Action a =player.getAction();
                if (a.c=='e'){
                        player.OnAbilityCast(enemies);
                }
                else{
                        Position pos=a.p;
                        Tile t = gameBoard.getTile(a.p);
                        player.interact(t);
                }
                for (Enemy enemy:enemies) {
                        if (!(enemy.elive()))
                             removeFrom.add(enemy);
                        else if (player.alive()){
                          enemy.getAction(player,gameBoard);}
                }
                onEnemyDeath();
                return gameBoard;
        }

        public void onEnemyDeath(){
                for (Enemy enemy:removeFrom)
                        enemies.remove(enemy);
                this.removeFrom=new LinkedList<Enemy>();
        }

        @Override
        public String toString() {
            return String.format("%s\n%s", gameBoard, player.describe());
        }
    }



