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

public class RogueTest {
    Rogue rogue;
    Monster monster;
    MessageCallback msg;
    Input input;
    private List<Enemy> enemies;

    @Before
    public void setUp() {
        rogue=new Rogue("test",100,100,100,100);
        msg=new CLI();
        input=new InputClass();
        rogue.initialize(new Position(0,0),msg,input);
        monster=new Monster('B',"testM",20,20,20,20,20);
        monster.setPosition(new Position(1,0));
        enemies=new LinkedList<Enemy>();
        enemies.add(monster);
    }
    @Test
    public void LevelUp(){
        rogue.setExperience(50);
        rogue.isLevelUp();
        Assert.assertEquals(100,rogue.getcurrentEnergy());
        Assert.assertEquals(120,rogue.getHealth().getHealthPool());
        Assert.assertEquals(120,rogue.getHealth().getHealthAmount());
        Assert.assertEquals(114,rogue.getAttack());
        Assert.assertEquals(102,rogue.getDefense());
        Assert.assertEquals(0,rogue.getExperience());
        Assert.assertEquals(2,rogue.getLevel());
    }
    @AfterEach
    @Test
    public void OnGameTick(){
        rogue.OnGameTick();
        Assert.assertEquals(100,rogue.getcurrentEnergy());
    }

}
