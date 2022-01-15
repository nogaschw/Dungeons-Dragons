package BL.Tiles;

import BL.Action;
import BL.MessageCallback;
import BL.Position;
import PL.Input;

import java.util.LinkedList;
import java.util.List;

//implements HeroicUnit
public abstract class Player extends Unit {
    public static final char playerTile = '@';
    protected static final int REQ_EXP = 50;
    protected static final int ATTACK_BONUS = 4;
    protected static final int DEFENSE_BONUS = 1;
    protected static final int HEALTH_BONUS = 10;
    protected Input input;

    protected int level;// Increased by gaining experience.
    protected int experience;//Increased by killing enemies

    //protected InputProvider inputProvider;

    protected Player(String name, int healthCapacity, int attack, int defense) {
        super(playerTile, name, healthCapacity, attack, defense);
        this.level = 1;
        this.experience = 0;
    }

    public Player initialize(Position position, MessageCallback messageCallback,  Input input){
        super.initialize(position, messageCallback);
        this.input=input;
        return this;
    }

    public Action getAction()
    {
        String s= input.getAction();
        Position pos =getPositionMap(s);
        return new Action(s.charAt(0),pos);
    }

    public void accept(Unit u){
        u.visit(this);
    }

    public void visit(Enemy e){
        super.battle(e);
        if (!e.alive()){
            swapPosition(e);//take the position of enemy
            onKill(e);
        }
    }

    public abstract void OnAbilityCast(List<Enemy> enemies);

    public void battleSpecialAbility(Enemy u, int attack)
    {
     //   messageCallback.send(String.format("%s engage in combat with %s.",getName(),u.getName()));
        int damageDone= Math.max(attack-u.defend(),0);
        if(damageDone>0) {
            u.health.reduceAmount(damageDone);
            messageCallback.send(String.format("%s hit %s for %d ability damage.",getName(),u.getName(),damageDone));
           // messageCallback.send(String.format("%s dealt %d damage to %s.",getName(),damageDone,u.getName()));
        }
        if(!u.alive())
            onKill(u);
    }

    public List<Enemy> EnemyInRange(List<Enemy> enemies, int range){
        List<Enemy> enemyInRange=new LinkedList<Enemy>();
        for (Enemy e:enemies) {
            if (this.position.range(e.getPosition())<range) {
                enemyInRange.add(e);
            }
        }
        return enemyInRange;
    }

    // When the player kills an enemy
    protected void onKill(Enemy e){
		this.experience=e.getExperience()+this.getExperience();
		messageCallback.send(String.format("%s died. %s gained %d experience",e.getName(),getName(),experience));
		e.onDeath();
        isLevelUp();
    }

    public void onDeath()
    {
        Tile('X');
        //messageCallback.send(getName()+" was killed by "+e.getName());
        messageCallback.send(String.format("YOU LOST."));
    }

    protected void UponLevelingUP(){
        //Upon leveling up the player gain:
        setExperience( getExperience() - levelUpRequirement());
        setLevel(getLevel() + 1);
        health.setHealthPool(health.getHealthPool() + gainHealth());
        health.setHealthAmount(health.getHealthPool());
        SetAttack(attack + gainAttack());
        SetDefense(defense + gainDefense());
    }

    public void isLevelUp(){
        while(experience>=50*level)
            levelUp();
    }
    // BL.Tiles.Player level up -
    protected void levelUp(){
         UponLevelingUP();
         messageCallback.send(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense",getName(),level,gainHealth(),gainAttack(),gainDefense()));
    }

    @Override
    public String toString() {
        return alive() ? super.toString() : "X";
    }

    // BL.Health / attack / defense gain when the player levels up - should be overriden by player subclasses and call super.gainHealth() for the base amount, then add the extra value
    protected int gainHealth(){
        return level * HEALTH_BONUS;
    }
    protected int gainAttack(){
        return level * ATTACK_BONUS;
    }
    protected int gainDefense(){
        return level * DEFENSE_BONUS;
    }

    private int levelUpRequirement(){
        return REQ_EXP * level;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {  this.level=level; }

    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) { this.experience=experience; }


    public void visit(Player p){ }

    public String describe() {
        return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement());
    }

}
