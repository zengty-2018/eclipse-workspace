import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * Run the DancingBug
 * @author z18342007
 *
 */
public class DancingBugRunner {
	public static void main(String[] args) {
		int n[] = {1,2,3,4,5,6,7,8};
		ActorWorld world = new ActorWorld();
		DancingBug alice = new DancingBug(n);
		world.add(new Location(6, 4), alice);
		world.show();
	}
}