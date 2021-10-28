package Part2.SpiralBug;

import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
	private int steps;
	private int sideLength;

	public SpiralBug(int n) {
		steps = 0;
		sideLength = n;
	}

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