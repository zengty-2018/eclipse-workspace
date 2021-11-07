import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * Running the King CrabRunner
 * @author z18342007
 *
 */
public class KingCrabRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int r = 4, c = 5;
        world.add(new Location(r, c), new KingCrab());
        r = 4; c = 0;
        world.add(new Location(r, c), new Rock());
        r = 4; c = 9;
        world.add(new Location(r, c), new Rock());
        
        for(int i = 0; i < 5; i++) {
        	world.add(new Location(7, i), new Rock());
        	world.add(new Location(8, i), new Flower());
        }
        for(int i = 5; i < 10; i++) {
        	world.add(new Location(7, i), new Rock());
        	world.add(new Location(8, i), new Rock());
        }
        world.add(new Location(9, 5), new KingCrab());
        world.add(new Location(5, 7), new Rock());
        world.add(new Location(2, 2), new Flower());
        world.add(new Location(3, 5), new Flower());
        world.add(new Location(3, 8), new Flower());
        world.add(new Location(6, 5), new Bug());
        world.add(new Location(5, 3), new Bug());
       
        world.show();
    }
}