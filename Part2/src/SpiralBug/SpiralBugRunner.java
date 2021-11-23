package SpiralBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

/**
 * Run the SpiralBug
 * @author z18342007
 *
 */
public class SpiralBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		SpiralBug alice = new SpiralBug(1);
		world.add(new Location(4, 4), alice);
		world.show();
	}
}