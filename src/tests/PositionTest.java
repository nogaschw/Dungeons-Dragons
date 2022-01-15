package tests;

import BL.Position;
import PL.GameManeger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {
    private Position p1;
    private Position p2;
    private Position p3;
    private Position p4;

    @Before
    public void setUp() {
         p1 = new Position(1,2);
         p2 = new Position(2,2);
         p3 = new Position(8,1);
         p4 = new Position(5,5);
    }

    @Test
    public void rangeTest() {
        Assert.assertEquals(1, p1.range(p2));
        Assert.assertEquals(1, p2.range(p1));
        Assert.assertEquals(5, p3.range(p4));
        Assert.assertEquals(5, p1.range(p4));
    }
}