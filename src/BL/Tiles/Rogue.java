package BL.Tiles;

import BL.Action;

import java.util.List;
import java.util.Random;

public class Rogue extends Player{
    private final String SpecialAbility = "Fan of Knives";
    private int cost;
    private int currentEnergy;
    private final int maxEnergy=100;
    public int getCost(){return  this.cost;}
    public int getcurrentEnergy(){return  this.currentEnergy;}

    public Rogue(String name, int healthCapacity, int attack, int defense, int cost) {
        super(name, healthCapacity, attack, defense);
        this.cost=cost;
        this.currentEnergy=maxEnergy;
    }
    public boolean castAbility(){
        return currentEnergy>=cost;
    }

    protected void UponLevelingUPRogue(){
        currentEnergy=maxEnergy;
        attack=attack+3*level;
    }

    protected void levelUp(){
        super.levelUp();
        UponLevelingUPRogue();
    }
    public void OnGameTick(){
        currentEnergy=Math.min(currentEnergy+10,100);
    }

    public Action getAction()
    {
        OnGameTick();
        return super.getAction();
    }

    @Override
    public void OnAbilityCast(List<Enemy> enemies){
        if (!castAbility())
            messageCallback.send(String.format("can't use special ability"+SpecialAbility));
        else {
            messageCallback.send(String.format(getName()+"cast "));
            currentEnergy=currentEnergy-cost;
            List<Enemy> enemyList = EnemyInRange(enemies,2);
            for (Enemy e:enemyList ) {
                super.battleSpecialAbility(e, getAttack());
            }
        }
    }


    @Override
    public void visit(Player p) { }


}