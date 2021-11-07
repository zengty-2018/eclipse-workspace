import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;

/**
 * ChameleonKid implement that changes its color to 
 * the color of one of the actors immediately in front 
 * or behind.
 * @author z18342007
 *
 */
public final class ChameleonKid extends ModifiedChameleonCritter {
	/**
	 * Overwrite the getActors() method, only add 
	 * the actors immediately in front or behind to
	 * ChameleonKid to the return list.
	 */
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		ArrayList<Location> locs = new ArrayList<Location>();

		Grid grid = getGrid();
		Location loc = getLocation();

		Location neighborLoc = loc.getAdjacentLocation(getDirection());
		if (grid.isValid(neighborLoc)) {
			locs.add(neighborLoc);
		}
		neighborLoc = loc.getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);
		if (grid.isValid(neighborLoc)) {
			locs.add(neighborLoc);
		}
		
		for (Location l : locs) {
			Actor actor = getGrid().get(l);
			if (actor != null) {
				actors.add(actor);
			}
		}
		
		return actors;
	}
}