package PL;
import BL.GameBoard;
import  BL.GameLevel;
import BL.MessageCallback;
import BL.Tiles.Enemy;
import BL.Tiles.Player;

import java.util.List;

public class GameManeger {
    private MessageCallback messageCallback;
    private List<Enemy> enemies;
    private Player player;
    private boolean deadPlayer;
    private boolean allEnemiesDead;
    private GameBoard gameBoard;
    private GameLevel gameLevel;

    public GameManeger(GameBoard gameBoard,List<Enemy> enemies,Player player,MessageCallback messageCallback){
        this.gameBoard=gameBoard;
        this.enemies=enemies;
        this.player=player;
        this.messageCallback=messageCallback;
        this.deadPlayer=false;
        this.allEnemiesDead=false;
        this.gameLevel=new GameLevel(gameBoard,player,enemies);
    }

    public Boolean getdeadPlayer(){return deadPlayer;}
    public Boolean getallEnemiesDead(){return allEnemiesDead;}

    public String  printGameBoard(){
       return gameBoard.toString(); }

    public  void gameTick()
    {
        messageCallback.send(printGameBoard());
        messageCallback.send(player.describe());
        gameBoard=gameLevel.round();
        if (enemies.size()==0)//check were the enemyes dead list.remove(e);
            allEnemiesDead=true;
        if (player.getTile()=='X') {
            deadPlayer = true;
            messageCallback.send(printGameBoard());
            messageCallback.send(player.describe());
            messageCallback.send("GAME OVER");
        }

    }

}
