import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * Running the Rock Hound.
 * @author z18342007
 *
 */
public final class RockHoundRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		int r = 4;
		int c = 4;
		world.add(new Location(4, 4), new RockHound());
		r = 4; c = 5;
		world.add(new Location(r, c), new Rock(Color.BLUE));
		r = 4; c = 3;
		world.add(new Location(r, c), new Rock(Color.PINK));
		r = 3; c = 5;
		world.add(new Location(r, c), new Rock(Color.RED));
		r = 3; c = 3;
		world.add(new Location(r, c), new Rock(Color.YELLOW));
		
		r = 6; c = 6;
		world.add(new Location(5, 8), new RockHound());
		r = 6; c = 7;
		world.add(new Location(r, c), new Rock(Color.BLUE));
		r = 7; c = 7;
		world.add(new Location(r, c), new Rock(Color.PINK));
		r = 8; c = 7;
		world.add(new Location(r, c), new Rock(Color.RED));
		r = 2; c = 7;
		world.add(new Location(r, c), new Rock(Color.YELLOW));
		r = 8; c = 2;
		world.add(new Location(r, c), new Rock(Color.GREEN));

		world.show();
	}
}