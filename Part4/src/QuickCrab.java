import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.*;

/**
 * QuickCrab implement that a faster Crab, it moves 2 step as 
 * Crab, the direction is randomly selected, , that are two 
 * spaces to its right or left, if that location and the 
 * intervening location are both empty.
 * @author z18342007
 *
 */
public class QuickCrab extends CrabCritter {
	/**
	 * Default Constructor, set the color of QuickCrab to GREEN.
	 */
	public QuickCrab() {
		setColor(Color.GREEN);
	}

	/**
	 * Judge whether the right and left 2 spaces is legal and empty.
	 * return the Location Array that fit the requirement.
	 */
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		Grid grid = getGrid();
		Location loc = getLocation();

		Location left_loc = loc.getAdjacentLocation(getDirection() + Location.LEFT);
		if (grid.isValid(left_loc) && grid.get(left_loc) == null) {
			Location leftTwo_loc = left_loc.getAdjacentLocation(getDirection() + Location.LEFT);
			if (grid.isValid(leftTwo_loc) && grid.get(leftTwo_loc) == null) {
				locs.add(leftTwo_loc);
			}
		}

		Location right_loc = loc.getAdjacentLocation(getDirection() + Location.RIGHT);
		if (grid.isValid(right_loc) && grid.get(right_loc) == null) {
			Location rightTwo_loc = right_loc.getAdjacentLocation(getDirection() + Location.RIGHT);
			if (grid.isValid(rightTwo_loc) && grid.get(rightTwo_loc) == null) {
				locs.add(rightTwo_loc);
			}
		}

		return locs;
	}

}