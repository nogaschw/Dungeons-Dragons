package BL.Tiles;

import BL.Action;

import java.util.List;

public class Warrior extends  Player {

    private int AbilityCoolDown;//Represents the number of game ticks required to pass before the warrior can cast the ability again.
    private int RemainingCoolDown;// Represents the number of ticks remained until the warrior can cast its special ability
    private final String SpecialAbility = "Avenger’s Shield";

    public Warrior(String name, int healthCapacity, int attack, int defense, int AbilityCoolDown) {
        super(name, healthCapacity, attack, defense);
        this.AbilityCoolDown = AbilityCoolDown;
        RemainingCoolDown = 0;
    }

    public int getRemainingCoolDown() {
        return RemainingCoolDown;
    }

    public int getAbilityCoolDown() {return this.AbilityCoolDown;}

    public Action getAction()
    {
        OnGameTick();
        return super.getAction();
    }

    public void OnGameTick()
    {
        if (RemainingCoolDown >0) {
            RemainingCoolDown = RemainingCoolDown - 1;
        }
    }

    protected void UponLevelingUPWarrior(){
        RemainingCoolDown=0;
        this.health.setHealthPool(this.health.getHealthPool()+5*level);
        SetAttack(this.attack+2*level);
        SetDefense(this.defense+1*level);
    }

    @Override
    protected void levelUp()
    {
        super.levelUp();
        UponLevelingUPWarrior();
    }

    public void setRemainingCoolDown(int RemainingCoolDown1)
    {
        this.RemainingCoolDown = RemainingCoolDown1;
    }

    public void OnAbilityCast(List<Enemy> enemies) {
        if (RemainingCoolDown> 0){
            messageCallback.send(String.format("%s you can't use your %s, there are %d ticks remained until you can cast your special ability", getName(), SpecialAbility, getRemainingCoolDown()));
        }
        else {
            messageCallback.send(getName()+" used "+SpecialAbility+" healing for "+(10 * defense));
            setRemainingCoolDown(AbilityCoolDown);
            this.health.setHealthAmount((Math.min(((getHealth().getHealthAmount()) + (10 * defense)), getHealth().getHealthPool())));
            List<Enemy> enemyList = EnemyInRange(enemies, 3);
            if (enemyList.size()!=0) {
                int randomNum = (int) (Math.random() * (enemyList.size() - 1));
                    int heal = 10 * defense;
                    if (heal + health.getHealthAmount() > health.getHealthPool()) {
                        heal = health.getHealthPool() - health.getHealthAmount();
                    }
                    super.battleSpecialAbility(enemyList.get(randomNum), (health.getHealthPool() / 10));
                    //this.health.setHealthAmount(getHealth().getHealthAmount() + heal);
                }
            }
        }

    public String describe() {
        return String.format("%s\t\tCoolDown: %s/%s", super.describe(), RemainingCoolDown,getAbilityCoolDown() );
    }
    @Override
    public void visit(Player p) {}//Imposible option

}

