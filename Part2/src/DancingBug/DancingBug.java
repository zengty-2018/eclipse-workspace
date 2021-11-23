package DancingBug;

import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;

/**
 * The Bug that dancing around
 * @author z18342007
 *
 */
public class DancingBug extends Bug {
	private int steps;
	private int sideLength;
	private int[] turns;
	private int count;

	/**
	 * Constructor with a steps array parameter
	 * @param n
	 */
	public DancingBug(int[] n) {
		steps = 0;
		sideLength = 2;
		count = 0;
		int length = n.length;
		turns = new int[length];
		System.arraycopy(n, 0, turns, 0, n.length);
	}

	/**
	 * The times of turn rely on the array
	 */
	public void act() {
		if (steps < sideLength && canMove()) {
			move();
			steps++;
		} else {
			for (int i = 0; i < turns[count]; i++) {
				turn();
			}
			
			if (count < turns.length - 1) {
				count++;
			} else {
				count = 0;
			}
			steps = 0;
		}
	}
}