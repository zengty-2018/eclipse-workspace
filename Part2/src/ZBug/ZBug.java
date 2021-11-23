package ZBug;

import info.gridworld.actor.Bug;

/**
 * The Bug that draw a Z
 * @author z18342007
 *
 */
public class ZBug extends Bug{
	private int steps;
	private int sideLength;
	private int finishedEdgeNum;

	/**
	 * Constructor, with a parameter edge length
	 * @param length
	 */
	public ZBug(int n) {
		steps = 0;
		sideLength = n;
		finishedEdgeNum = 0;
		setDirection(90);
	}

	/**
	 * Divide the 'Z' to 3 pieces, 2 straight line and 1 oblique line,
	 * and then implement one by one
	 */
	public void act() {
		if (finishedEdgeNum < 3 && steps < sideLength && canMove()) {
			move();
			steps++;
		} else {
			if (finishedEdgeNum == 2 || steps < sideLength) {
				return;
			} else if (finishedEdgeNum == 0) {
				finishedEdgeNum++;
				int turns = 3;
				for (int i = 0; i < turns; i++) {
					turn();
				}
				steps = 0;
			} else if (finishedEdgeNum == 1) {
				int turns = 5;
				for (int i = 0; i < turns; i++) {
					turn();
				}
				finishedEdgeNum++;
				steps = 0;
			}
		}
	}
}