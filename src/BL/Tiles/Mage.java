package BL.Tiles;

import BL.Action;

import java.util.List;

public class Mage extends  Player {
    private int ManaPool;//, holds the maximal value of mana
    private int CurrentMana;//current amount of mana
    private int ManaCost;//ability cost.
    private  int SpellPower;// ability scale factor
    private  int HitsCount;//maximal number of times a single cast of the ability can hit
    private int AbilitRange;//
    private final String SpecialAbility = "Fan of Knives";


    public Mage(String name, int healthCapacity, int attack, int defense, int ManaPool, int ManaCost, int SpellPower, int HitsCount, int AbilitRange) {
        super(name, healthCapacity, attack, defense);
        this.ManaPool=ManaPool;
        this.CurrentMana=ManaPool/4;
        this.ManaCost=ManaCost;
        this.SpellPower=SpellPower;
        this.HitsCount=HitsCount;
        this.AbilitRange=AbilitRange;
    }
    public void OnGameTick()
    {
        SetCurrentMana(Math.min(GetManaPool(),(GetCurrentMana()+level)));
    }

    protected void UponLevelingUPMage(){
        SetManaPool(GetManaPool()+25*level);
        SetCurrentMana(Math.min(GetCurrentMana()+(GetManaPool()/4),GetManaPool()));
        SetSpellPower(GetSpellPower()+10*level);
    }
    public Action getAction()
    {
        OnGameTick();
        return super.getAction();
    }

    protected void levelUp()
    {
        //After gaining (50 × level) experience points
        super.levelUp();
        UponLevelingUPMage();
    }

    public void OnAbilityCast(List<Enemy>enemies) {
        SetCurrentMana(GetCurrentMana() - ManaCost);
        int hits = 0;
            if (CurrentMana < ManaCost) {
                messageCallback.send(String.format("%s tried to cast Blizzard, but there was not enough mana: %s /%s", getName(), GetCurrentMana(), GetManaCost()));
            } else {
                List<Enemy> enemyList = EnemyInRange(enemies, AbilitRange);
                messageCallback.send(String.format("%s cast Blizzard", getName()));
                while ((hits < GetHitsCount()) && (enemyList.size()!=0)) {
                    int randomNum = (int) (Math.random() * (enemyList.size() - 1));
                    Enemy RandomEnemy = enemyList.get(randomNum);
                    super.battleSpecialAbility(RandomEnemy, SpellPower);
                    hits++;
                    if (!RandomEnemy.elive())
                        enemyList.remove(RandomEnemy);
                }
        }
    }

    public void SetHitsCount(int HitsCount){this.HitsCount=HitsCount;}
    public  int GetHitsCount(){return this.HitsCount;}
    public void SetManaPool(int manaPool){this.ManaPool=manaPool;}
    public  int GetManaPool(){return this.ManaPool;}
    public void SetManaCost(int manaPool){this.ManaCost=manaPool;}
    public  int GetManaCost(){return this.ManaCost;}
    public  int GetCurrentMana(){return this.CurrentMana;}
    public void SetCurrentMana(int CurrentMana){this.CurrentMana=CurrentMana;}
    public  int GetSpellPower(){return this.SpellPower;}
    public void SetSpellPower(int SpellPower){this.SpellPower=SpellPower;}


    @Override
    public void visit(Player p) { }

    public String describe() {
        return String.format("%s\t\tMana: %d/%d\t\tSpell Power: %d", super.describe(), GetCurrentMana(), GetManaPool(), GetSpellPower());
    }
}