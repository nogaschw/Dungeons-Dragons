package BL.Tiles;

import BL.Action;
import BL.Health;
import BL.MessageCallback;
import BL.Position;

import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public abstract class Unit extends Tile {
    protected MessageCallback messageCallback;

    protected String name;
    protected Health health;
    protected int attack;
    protected int defense;

    private Map<String, Supplier<Position>> map = Map.of(
            "w", () -> position.translate(0, -1),
            "s", () -> position.translate(0, 1),
            "a", () -> position.translate(-1, 0),
            "d", () -> position.translate(1, 0),
            "e", () -> position.translate(0, 0),
            "q", () -> position.translate(0, 0));


    public Position getPositionMap(String string)
    {
        Supplier<Position> supplier=map.get(string);
        return supplier.get();
    }


    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }
    public void setHealth(Health health) {
        this.health=health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void SetAttack(int attack){this.attack=attack;}
    public void SetDefense(int defense){this.defense=defense;}

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile);
        this.name = name;
        this.health = new Health(healthCapacity, healthCapacity);
        this.attack = attack;
        this.defense = defense;
    }

    protected void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position);
        this.messageCallback = messageCallback;
    }

    protected int attack(){
        Random r=new Random();
        int result=r.nextInt(attack+1);
        messageCallback.send(String.format("%s rolled %d attack point.",getName(),result));
        return result;
    }

    public int defend(){
        Random r=new Random();
        int result=r.nextInt(defense+1);
        messageCallback.send(String.format("%s rolled %d defense point.",getName(),result));
        return result;
    }

    // What happens when the unit dies
    public abstract void onDeath();


    public void interact(Tile tile){
        tile.accept(this);
    }

    public void visit(Empty e){
        swapPosition(e);
    }
    public void visit(Wall w){//stack in the wall
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    protected void battle(Unit u){
        messageCallback.send(String.format("%s engage in combat with %s.",getName(),u.getName()));
        messageCallback.send(this.describe());
        messageCallback.send(u.describe());
        int damageDone= Math.max(attack()-u.defend(),0);
        if(damageDone>0) {
            u.health.reduceAmount(damageDone);
            messageCallback.send(String.format("%s dealt %s damage to %s.",getName(),damageDone,u.getName()));
        }
    }

    public boolean alive(){
        return getHealth().getHealthAmount()>0;
    }
    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }

}
