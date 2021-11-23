package SpiralBug;

import info.gridworld.actor.Bug;

/**
 * The Bug that side length increase
 * @author z18342007
 *
 */
public class SpiralBug extends Bug {
	private int steps;
	private int sideLength;

	/**
	 * Constructor with initial sideLength
	 * @param n
	 */
	public SpiralBug(int n) {
		steps = 0;
		sideLength = n;
	}

	/**
	 * Each time turn 90 degrees, the sideLength increase 1
	 */
	public void act() {
		if (steps < sideLength && canMove()) {
			move();
			steps++;
		} else {
			turn();
			turn();
			steps = 0;
			sideLength++;
		}
	}
}