package BL.Tiles;

import BL.GameBoard;
import BL.Position;
import PL.Input;

public class Monster extends Enemy {
    private int visionRange;

    public Monster(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue, int visionRange) {
        super(tile, name, healthCapacity, attack, defense,experienceValue);
        this.visionRange = visionRange;
    }
    public void Down(GameBoard board){
        this.interact(board.getTile(new Position(this.position.getX(),this.position.getY()+1)));
    }
    public void Up(GameBoard board){
        this.interact(board.getTile(new Position(this.position.getX(),this.position.getY()-1)));
    }
    public void Left(GameBoard board){
        this.interact(board.getTile(new Position((this.position.getX())-1,this.position.getY())));
    }
    public void Right(GameBoard board){
        this.interact(board.getTile(new Position(this.position.getX()+1,this.position.getY())));
    }
    public void getAction(Player player, GameBoard board){
        if(this.position.range(player.position)<visionRange){
            int dx=this.position.getX()-player.position.getX();
            int dy=this.position.getY()-player.position.getY();
            if (Math.abs(dx)>Math.abs(dy)) {
                if (dx > 0)
                    Left(board);
                else
                    Right(board);
            }
            else{
                if (dy>0)
                    Up(board);
                else
                    Down(board);
            }
        }
        else
            RandomMove(board);
    }
    public void RandomMove(GameBoard board){
        int r= (int)(Math.random() * 5);
        if (r==0)
            Left(board);
        if (r==1)
            Right(board);
        if (r==2)
            Up(board);
        if (r==3)
            Down(board);
    }

     @Override
     public void onDeath() {
        super.OnDeath();
     }

     public String describe() {
        return String.format("%s\t\tExperience Value: %s\t\tVision Range: %s", super.describe(), getExperience(),visionRange);
     }

 }
