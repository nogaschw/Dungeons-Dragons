package BL.Tiles;
import BL.*;
import PL.Input;

public abstract class Enemy extends Unit {
    private int experienceValue;

    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense,int experienceValue) {
        super(tile, name, healthCapacity, attack, defense);
        this.experienceValue=experienceValue;
    }

    public void initialize(Position position, MessageCallback messageCallback)
    {
        super.initialize(position,messageCallback);
    }

    public int getExperience(){
        return experienceValue;
    }

    public boolean elive(){
            return ((this.getHealth().getHealthAmount()>0));}

    public void accept(Unit u){
        u.visit(this);
    }

    public void visit(Player p){
        super.battle(p);
        if (!p.alive()){
            p.onDeath();
        }
    }

    public void visit(Enemy e){};
    public void OnDeath()
    {
        Tile('.');
    }


    public abstract void getAction(Player player, GameBoard gameBoard);
}
