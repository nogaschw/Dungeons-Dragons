package tests;

import BL.Tiles.Monster;
import BL.Tiles.Traps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

public class EnemyTests {
        Traps traps;
        Monster monster;

        @Before
        public void setUp() {
            traps=new Traps('B',"testT",10,10,10,10,1,1);
            monster=new Monster('Q',"testM",10,10,10,10,10);
        }
        @Test
        public void OnDeath(){
            traps.OnDeath();
            Assert.assertEquals('.',traps.getTile());
            monster.OnDeath();
            Assert.assertEquals('.',monster.getTile());
        }
}
