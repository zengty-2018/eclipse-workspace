import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

/**
 * RockHound implement that removes any rocks in that list from the grid,
 * and it have the same move way as Critter.
 * @author z18342007
 *
 */
public class RockHound extends Critter {
	public void processActors(ArrayList<Actor> actors) {
		for (Actor a : actors) {
			if (a instanceof Rock) {
				a.removeSelfFromGrid();
			}
		}
	}
}