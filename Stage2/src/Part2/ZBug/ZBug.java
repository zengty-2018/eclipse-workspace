package Part2.ZBug;

import info.gridworld.actor.Bug;

public class ZBug extends Bug{
	private int steps;
	private int sideLength;
	private int finishedEdgeNum;

	public ZBug(int n) {
		steps = 0;
		sideLength = n;
		finishedEdgeNum = 0;
		setDirection(90);
	}

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
