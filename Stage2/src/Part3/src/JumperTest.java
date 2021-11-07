import static org.junit.Assert.assertEquals;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The JUnitTest of jumper.
 * Design 8 tests to test the jumper.
 * @author z18342007
 *
 */
public class JumperTest {
    private ActorWorld world = new ActorWorld();
    private Jumper jumper1 = new Jumper(Color.BLUE);
    private Jumper jumper2 = new Jumper(Color.GREEN);

    /**
     * Preparations of the test.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        world.add(new Location(0, 0), jumper1);
        world.add(new Location(8, 8), jumper2);
    }

    /**
     * Test the constructor with a color parameter.
     */
    @Test
    public void testJumperConstructor() {
        assertEquals(Color.BLUE, jumper1.getColor());
        assertEquals(Color.GREEN, jumper2.getColor());
    }
    
    /**
     * Test the situation that jumping over the Rock and flower.
     */
     @Test
    public void testJumper() {
        jumper1.moveTo(new Location(5, 5));
        world.add(new Location(4, 5), new Rock());
        world.add(new Location(2, 5), new Flower());
        jumper1.act();
        assertEquals(new Location(3, 5), jumper1.getLocation());
        assertEquals(0, jumper1.getDirection());
        jumper1.act();
        assertEquals(new Location(1, 5), jumper1.getLocation());
        assertEquals(0, jumper1.getDirection());
    }
    
    /**
     * Test the next Location is the Rock.
     */
    @Test
    public void testNextIsRock() {
        jumper1.moveTo(new Location(5, 5));
        world.add(new Location(3, 5), new Rock());
        jumper1.act();
        assertEquals(new Location(5, 5), jumper1.getLocation());
        assertEquals(Location.NORTHEAST, jumper1.getDirection());
    }

    /**
     * Test the next Location is the Flower.
     */
    @Test
    public void testNextIsFlower() {
        jumper1.moveTo(new Location(5, 5));
        world.add(new Location(3, 5), new Flower());
        jumper1.act();
        assertEquals(new Location(3, 5), jumper1.getLocation());
    }
    
    /**
     * Test the situation that provoking act and cause out of
     * grid side.
     */
    @Test
    public void testActOutOfGrid() {
        jumper1.moveTo(new Location(0, 1));
        jumper1.setDirection(Location.WEST);
        jumper1.act();
        assertEquals(new Location(0, 1), jumper1.getLocation());
        assertEquals(Location.NORTHWEST, jumper1.getDirection());
    }

    /**
     * Test the situation that provoke move and cause out of
     * grid side.
     */
    @Test
    public void testMoveOutGrid() {
    	jumper1.moveTo(new Location(0, 1));
        jumper1.setDirection(Location.WEST);
        jumper1.move();
        assertEquals(null, jumper1.getLocation());
    }
    
    /**
     * Test the situation that another actor (not a flower or a rock) is 
     * in the cell that is two cells in front of the jumper
     */
    @Test
    public void testActToAnotherActor() {
    	jumper1.moveTo(new Location(5,5));
    	world.add(new Location(3, 5), new Bug());
    	jumper1.act();
        assertEquals(new Location(5, 5), jumper1.getLocation());
        assertEquals(Location.NORTHEAST, jumper1.getDirection());
    }

    /**
     * Test the situation that a jumper do if it encounters another jumper in its path.
     */
    @Test
    public void testMultiMove() {
        jumper1.moveTo(new Location(0, 5));
        jumper1.setDirection(Location.SOUTH);
        jumper2.moveTo(new Location(8, 5));
        
        jumper1.act();
        jumper2.act();
        assertEquals(new Location(2, 5), jumper1.getLocation());
        assertEquals(new Location(6, 5), jumper2.getLocation());
        
        jumper1.act();
        jumper2.act();
        assertEquals(new Location(4, 5), jumper1.getLocation());
        assertEquals(new Location(6, 5), jumper2.getLocation());
        assertEquals(Location.NORTHEAST,  jumper2.getDirection());
    }

    /**
     * Test the situation that act obliquely
     */
    @Test
    public void testActObliquely() {
    	jumper1.moveTo(new Location(5,5));
    	jumper1.setDirection(Location.NORTHEAST);
    	jumper1.act();
    	assertEquals(new Location(3, 7), jumper1.getLocation());
    }
    
    @After
    public void end() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                world.remove(new Location(i, j));
            }
        }
    }

}