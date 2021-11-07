import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.*;
import java.util.ArrayList;
import java.awt.Color;

/**
 * KingCrab implement that causes each actor that 
 * it processes to move one location further away 
 * from the KingCrab. If the actor cannot move away, 
 * the KingCrab removes it from the grid.
 * The movement of KingCrab is the same as Crab.
 * @author z18342007
 *
 */
public class KingCrab extends CrabCritter {
	/**
	 * The default constructor, set the color of KingCrab to YELLOW.
	 */
	public KingCrab() {
		setColor(Color.YELLOW);
	}

	/**
	 * KingCrab causes each actor that it processes to 
	 * move one location further away from the KingCrab. 
	 * If the actor cannot move away, the KingCrab removes 
	 * it from the grid.
	 * The Rock and Flower can't move, so remove them directly;
	 * other Actor judge whether can move, if can just move to
	 * new position, otherwise remove from the grid.
	 */
	public void processActors(ArrayList<Actor> actors)
    {
        for(Actor a: actors)
        {
            if(a instanceof Rock || a instanceof Flower)
            {
                a.removeSelfFromGrid();
            }else
            {
                Grid gr = getGrid();
                Location loc = a.getLocation();
                int dir = (loc.getDirectionToward(getLocation()) + Location.HALF_CIRCLE) % Location.FULL_CIRCLE;
                Location newLoc = loc.getAdjacentLocation(dir);
                if(gr.isValid(newLoc))
                {
                    a.moveTo(newLoc);
                }else
                {
                    a.removeSelfFromGrid();
                }
            }
        }
    }

}