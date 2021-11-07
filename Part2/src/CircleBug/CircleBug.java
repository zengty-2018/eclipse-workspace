import info.gridworld.actor.Bug;

/**
 * The Bug that draw a circle
 * @author z18342007
 *
 */
public class CircleBug extends Bug {
	private int steps;
	private int sideLength;

	/**
	 * Constructor, with a parameter edge length
	 * @param length
	 */
	public CircleBug(int length) {
		steps = 0;
		sideLength = length;
	}

	/**
	 * Each time it run length steps, turn 45 degrees
	 */
	public void act() {
		if (steps < sideLength && canMove()) {
			move();
			steps++;
		} else {
			turn();
			steps = 0;
		}
	}
}