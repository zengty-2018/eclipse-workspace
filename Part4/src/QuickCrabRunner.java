import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * Running the QuickCrabRunner
 * @author z18342007
 *
 */
public final class QuickCrabRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		int r = 6, c = 5;
		world.add(new Location(r, c), new QuickCrab());
		r = 2; c = 1;
		world.add(new Location(r, c), new QuickCrab());
		r = 2; c = 5;
		world.add(new Location(r, c), new CrabCritter());
		r = 6; c = 2;
		world.add(new Location(r, c), new Rock());
		r = 2; c = 7;
		world.add(new Location(r, c), new Rock());
		world.show();
	}
}