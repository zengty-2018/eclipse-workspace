import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * Run the ZBug
 * @author z18342007
 *
 */
public class ZBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		ZBug alice = new ZBug(4);
		alice.setColor(Color.RED);
		ZBug bob = new ZBug(4);
		bob.setColor(Color.BLUE);
		
		world.add(new Location(2, 2), alice);
		world.add(new Location(8, 8), bob);
		world.show();
	}
}