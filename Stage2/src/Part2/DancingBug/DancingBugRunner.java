package Part2.DancingBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class DancingBugRunner {
	public static void main(String[] args) {
		int n[] = {1,2,3,4,5,6,7,8};
		ActorWorld world = new ActorWorld();
		DancingBug alice = new DancingBug(n);
		world.add(new Location(9, 9), alice);
		world.show();
	}
}