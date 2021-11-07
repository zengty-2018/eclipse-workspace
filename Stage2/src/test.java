import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class test {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		Bug test = new Bug();
		world.add(test);
		Grid<Actor> tempGrid = test.getGrid();
		Location tempLocation = test.getLocation();
		test.removeSelfFromGrid();
		test.putSelfInGrid(tempGrid, tempLocation);
	}
}
