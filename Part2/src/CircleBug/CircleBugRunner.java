package CircleBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

/**
 * Run the CircleBug
 * @author z18342007
 *
 */
public class CircleBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		CircleBug alice = new CircleBug(2);
		world.add(new Location(6, 2), alice);
		world.show();
	}
}