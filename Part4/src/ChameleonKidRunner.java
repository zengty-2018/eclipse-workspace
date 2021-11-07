import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * Running the ChameleonKidRunner.
 * @author z18342007
 *
 */

public final class ChameleonKidRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();

		int r = 3;
		int c = 3;
		world.add(new Location(r, c), new ChameleonKid());
		r = 6; c = 7;
		world.add(new Location(r, c), new ChameleonKid());
		r = 2; c = 3;
		world.add(new Location(r, c), new Rock(Color.RED));
		r = 7; c = 7;		
		world.add(new Location(r, c), new Rock(Color.YELLOW));
		r = 5; c = 3;		
		world.add(new Location(r, c), new Rock());
		world.show();
	}
}