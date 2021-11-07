import java.awt.Color;
import info.gridworld.grid.*;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;

/**
 * 
 * A Jumper is an extends of Bug that can move two steps every time. But it 
 * won't drops flowers as it moves. 
 * This class overwrite the method 'move' and 'canMove'.
 * @author z18342007
 *
 */
public class Jumper extends Bug {
    /**
     * Default Constructor.
     * Constructs a red jumper.
     */
	public Jumper() {
		setColor(Color.RED);
	}

    /**
     * Constructs a jumper with a given color.
     * @param bugColor the color for this bug
     */
	public Jumper(Color jumperColor) {
		setColor(jumperColor);
	}

    /**
     * Moves the jumper forward, putting nothing into the location it previously
     * occupied.
     * Move 2 steps every time as bug.
     * If the next Location is not valid, the jumper will be removed in the grid.
     */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
		
		if (gr.isValid(next)) {
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
	}

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * First get the next Location, then judge the Location whether is empty or
     * contains a flower.
     * @return true if this bug can move.
     */
	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return false;
		}
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
		if (!gr.isValid(next)) {
			return false;
		}
		Actor neighbor = gr.get(next);
		
		return (neighbor == null) || (neighbor instanceof Flower);
	}

}