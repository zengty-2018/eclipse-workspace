package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	private Integer[] directionCount;
	private Integer[] directionChoose;
	private int DIRECTION_NUM = 4;
	

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		
		//Initialization
		directionCount = new Integer[DIRECTION_NUM];
		directionChoose = new Integer[DIRECTION_NUM];
		for (int i = 0; i < DIRECTION_NUM; i++) {
			directionCount[i] = 1;
			directionChoose[i] = 0;
		}
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if(crossLocation.empty()){
			crossLocation.push(getValid(getLocation()));
		}
		
		boolean willMove = canMove();
		if (isEnd == true) {
			//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if(willMove){
			//have the direction to move
			move();
			//increase step count when move 
			stepCount++;
		} else {
			// there is no direction available, return to the previous location
			recall();
			//increase step count when move 
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		
		ArrayList<Location> valid = getValidAdjacent(loc);
		//add the location now to the first of the list to back when going to the false way
		
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		// the valid at least have the location that it locate in, so when the array list
		// size less than 2 means the bug can not move forward
		if(crossLocation.peek().size() <= 1){
			return false;
		}
		return true;
	}
	
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;

		Location loc = getLocation();
		next = getRandDirection();
		
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);

		// update last parameter and valid direction after move
		last = loc;
		crossLocation.push(getValid(next));
	}
	
	/**
	 * Recall to path to return to the previous location to choose 
	 * another way to go when this way is unavailable.
	 */
	private void recall() {
		Location loc = getLocation();
		
		// Get the previous ajacent list
		crossLocation.pop();
		ArrayList<Location> previousArrayList = crossLocation.pop();
		//remove the false direction
		previousArrayList.remove(loc);
		// get the previous location
		if(crossLocation.empty()) {
			loc = previousArrayList.get(0);
		} else {
			loc = crossLocation.peek().get(0);
		}
		// push the new arrayList to the stack
		crossLocation.push(previousArrayList);
		
		int dir = getLocation().getDirectionToward(last);
		// move the bug
		setDirection(dir);
		moveTo(last);
		//update the last and directionCount
		last = loc;
		directionCount[((dir + Location.HALF_CIRCLE) / Location.EAST) % DIRECTION_NUM]--;
	}
	
	/**
	 * Randomly select a direction to move in the valid direction
	 * The probability should conform to the directionCount
	 * @return: the selected Location
	 */
	private Location getRandDirection() {
		Random random = new Random();
		ArrayList<Location> validLocation = crossLocation.peek();
		int sum = 0, index = 0, bound = 0, count = 0;
		Location ret;
		
		// calculate the direction that can go and 
		// the sum of all available directionCount for selecting direction
		for(int i = 1; i < validLocation.size(); i++){
			int j = getLocation().getDirectionToward(validLocation.get(i))/Location.EAST;
			directionChoose[j] = 1;
			sum += directionCount[j];
		}
		
		// randomly select the direction based on the directionCount
		int num = random.nextInt(sum);
		for(int i = 0; i < DIRECTION_NUM; i++){
			if(directionChoose[i] == 1){
				count++;
				bound += directionCount[i];
				if(num < bound){
					index = count;
					break;
				}
			}
		}
		for(int i = 0; i < DIRECTION_NUM; i++){
			directionChoose[i] = 0;
		}
		
		// update the directionCount
		ret = validLocation.get(index);
		directionCount[getLocation().getDirectionToward(ret)/Location.EAST]++;
		
		return ret;
	}
	
	/**
	 * Get the valid adjacency of the given location
	 * @param loc: given location
	 * @return: the ArrayList of the valid adjacency
	 */
	private ArrayList<Location> getValidAdjacent(Location loc) {
		Grid<Actor> gr = getGrid();
		ArrayList<Location> ret = new ArrayList<Location>();
		
		// add the now location to the ArrayList for recall
		ret.add(getLocation());
		for(int i = 0; i < DIRECTION_NUM; i++){
			// 4 direction
			Location adjacent = loc.getAdjacentLocation(Location.HALF_CIRCLE / 2 * i);
			// last location and out of grid location will not be added
			if(adjacent.equals(last) || !gr.isValid(adjacent)){
				continue;
			}

			Actor actor = gr.get(adjacent);
			if(actor != null){
				// judge whether get to the end
				if(actor.getColor().equals(new Color(255, 0 , 0))){
					isEnd = true;
					break;
				}
				// the flower's and rock's location will not be added in the ret
				continue;
			}
			ret.add(adjacent);
		}
		
		return ret;
	}
}