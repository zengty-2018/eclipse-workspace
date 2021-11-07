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
		j.setDirection(45);
		world.add(new Location(4, 4), j);
		world.add(new Location(1, 4), new Rock());
		world.add(new Location(2, 6), new Rock());
		world.add(new Location(2, 4), new Flower());
		world.show();
	}
}