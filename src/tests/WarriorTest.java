package tests;

import BL.GameBoard;
import BL.GameLevel;
import BL.MessageCallback;
import BL.Position;
import BL.Tiles.*;
import PL.CLI;
import PL.GameManeger;
import PL.Input;
import PL.InputClass;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WarriorTest {
    Warrior warrior;
    Monster monster;
    MessageCallback msg;
    Input input;
    private List<Enemy> enemies;

    @Before
    public void setUp() {
        warrior=new Warrior("test",100,100,100,100);
        msg=new CLI();
        input=new InputClass();
        warrior.initialize(new Position(0,0),msg,input);
        monster=new Monster('B',"testM",20,20,20,20,20);
        monster.setPosition(new Position(1,0));
        enemies=new LinkedList<Enemy>();
        enemies.add(monster);
    }
    @Test
    public void LevelUp(){
        warrior.setExperience(50);
        warrior.isLevelUp();
        Assert.assertEquals(0,warrior.getRemainingCoolDown());
        Assert.assertEquals(130,warrior.getHealth().getHealthPool());
        Assert.assertEquals(120,warrior.getHealth().getHealthAmount());
        Assert.assertEquals(112,warrior.getAttack());
        Assert.assertEquals(104,warrior.getDefense());
        Assert.assertEquals(0,warrior.getExperience());
        Assert.assertEquals(2,warrior.getLevel());
    }
    @AfterEach
    @Test
    public void OnGameTick(){
        warrior.setRemainingCoolDown(3);
        warrior.OnGameTick();
        Assert.assertEquals(2,warrior.getRemainingCoolDown());
        warrior.OnGameTick();
        Assert.assertEquals(1,warrior.getRemainingCoolDown());
    }

}
