import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * ModifiedChameleonCritter implement that when the list of 
 * actors to process is empty, the color of the ChameleonCritter 
 * will darken (like a flower).
 * @author z18342007
 *
 */
public class ModifiedChameleonCritter extends Critter
{
	private static final double DARKENING_SPEED = 0.1; 
	
	/**
	 * Randomly select a neighbor to change the color as it.
	 * When there is no neighbor around, provoke darken() to
	 * make the color dark.
	 */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0) {
        	darken();
        	return;
        }
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
    }

    /**
     * Turns to the direction that it move.
    */ 
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
    
    /**
     * Make the color of ChameleonCritter become dark, make the 
     * RGB decrease DARKENING_SPEED.
     */
    public void darken() {
    	Color c = getColor();
 		int red = (int) (c.getRed() * (1 - DARKENING_SPEED));
 		int green = (int) (c.getGreen() * (1 - DARKENING_SPEED));
 		int blue = (int) (c.getBlue() * (1 - DARKENING_SPEED));
 		setColor(new Color(red, green, blue)); 
    }
}