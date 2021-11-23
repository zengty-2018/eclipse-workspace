import info.gridworld.grid.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;

/**
 * A JumperRunner is a Runner of class Jumper.
 * @author z18342007
 *
 */
public class JumperRunner{
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		Jumper j = new Jumper();
		world.add(new Location(6, 4), j);
		world.add(new Location(5, 4), new Rock());
		world.add(new Location(0, 4), new Rock());
		world.add(new Location(3, 4), new Flower());
		world.show();
	}
}