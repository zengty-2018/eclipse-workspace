import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * Running the modifiedChameleonCritter,
 * Copy from ChameleonCritterRunner.
 * @author z18342007
 *
 */
public class ModifiedChameleonCritterRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		world.add(new Location(5, 5), new Rock(Color.PINK));
		world.add(new Location(1, 5), new Rock(Color.RED));
		world.add(new Location(7, 2), new Rock(Color.YELLOW));
		world.add(new Location(4, 4), new ModifiedChameleonCritter());
		world.add(new Location(5, 8), new ModifiedChameleonCritter());
		world.show();
	}
}