package tests;

import BL.MessageCallback;
import BL.Position;
import BL.Tiles.Enemy;
import BL.Tiles.Mage;
import BL.Tiles.Monster;
import BL.Tiles.Warrior;
import PL.CLI;
import PL.Input;
import PL.InputClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.LinkedList;
import java.util.List;

public class MagaTest {
        Mage maga;
        Monster monster;
        MessageCallback msg;
        Input input;
        private List<Enemy> enemies;

        @Before
        public void setUp() {
            maga=new Mage("test",100,100,100,100,100,20,20,20);
            msg=new CLI();
            input=new InputClass();
            maga.initialize(new Position(0,0),msg,input);
            monster=new Monster('B',"testM",20,20,20,20,20);
            monster.setPosition(new Position(1,0));
            enemies=new LinkedList<Enemy>();
            enemies.add(monster);
        }
        @Test
        public void LevelUp(){
            maga.setExperience(50);
            maga.isLevelUp();
            Assert.assertEquals(150,maga.GetManaPool());
            Assert.assertEquals(40,maga.GetSpellPower());
            Assert.assertEquals(62,maga.GetCurrentMana());
            Assert.assertEquals(120,maga.getHealth().getHealthAmount());
            Assert.assertEquals(108,maga.getAttack());
            Assert.assertEquals(102,maga.getDefense());
            Assert.assertEquals(0,maga.getExperience());
            Assert.assertEquals(2,maga.getLevel());
        }
        @AfterEach
        @Test
        public void OnGameTick(){
            maga.OnGameTick();
            Assert.assertEquals(26,maga.GetCurrentMana());
        }



}
