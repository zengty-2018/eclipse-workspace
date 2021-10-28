package Part2.CircleBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class CircleBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		CircleBug alice = new CircleBug(3);
		//alice.setColor(Color.BLUE);
		CircleBug bob = new CircleBug(1);	
		world.add(new Location(8, 8	), alice);
		world.show();
	}
}