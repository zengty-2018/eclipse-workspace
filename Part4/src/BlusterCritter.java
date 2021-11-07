import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.Color;

/**
 * BlusterCritter implement that A BlusterCritter 
 * looks at all of the neighbors within two steps 
 * of its current location, If there are fewer than 
 * c critters, the BlusterCritter’s color gets brighter 
 * (color values increase). If there are c or more critters, 
 * the BlusterCritter’s color darkens (color values decrease).
 * @author z18342007
 *
 */
public class BlusterCritter extends Critter {
	private int courage;
	private static final double DARKING_SPEED = 0.1;
	private static final double BRIGHTENING_SPEED = 20;

	/**
	 * Default Constructor, set the courage 2 and the
	 * color PINK.
	 */
	public BlusterCritter() {
		super();
		courage = 2;
		super.setColor(Color.PINK);
	}

	/**
	 * Constructor with a parameter c, set the courage to c
	 * and the color PINk.
	 * @param c
	 */
	public BlusterCritter(int c) {
		super();
		courage = c;
		super.setColor(Color.PINK);
	}

	/**
	 * Get all Actors in two steps of its current location(24 locations).
	 */
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Location loc = getLocation();

		Grid gr = getGrid();
		for (int i = loc.getRow() - 2; i <= loc.getRow() + 2; i++) {
			for (int j = loc.getCol() - 2; j <= loc.getCol() + 2; j++) {
				Location temp = new Location(i, j);
				if (gr.isValid(temp)) {
					Actor actor = getGrid().get(temp);
					if (actor != null && actor != this) {
						actors.add(actor);
					}
				}
			}
		}

		return actors;
	}

	/**
	 * If the number of Critter in two steps less than courage,
	 * the BlusterCritter become Brighter, else become darker.
	 */
	public void processActors(ArrayList<Actor> actors) {
		int count = getNeighborCount(actors);
		
		if (count > courage) {
			Darken();
		} else {
			Brighten();
		}
	}

	/**
	 * Calculator the number of Critter in two step locations.
	 * @param actors
	 * @return
	 */
	private int getNeighborCount(ArrayList<Actor> actors) {
		int ret = 0;
		for (Actor actor : actors) {
			if (actor instanceof Critter) {
				ret++;
			}
		}
		
		return ret;
	}
	
	/**
	 * Making the color of BlusterCritter become brighter, 
	 * directly add 20 to its RGB.
	 */
	private void Brighten() {
		Color color = getColor();
		int red = Math.min((int)(color.getRed()+BRIGHTENING_SPEED), 255);
		int green = Math.min((int)(color.getGreen()+BRIGHTENING_SPEED), 255);
		int blue = Math.min((int)(color.getBlue()+BRIGHTENING_SPEED), 255);
		setColor(new Color(red, green, blue));
	}

	/**
	 * Making the color of BlusterCritter become darker, 
	 * decrease 10% each time.
	 */
	private void Darken() {
		Color color = getColor();
		int red = (int)(color.getRed()*(1-DARKING_SPEED));
		int green = (int)(color.getGreen()*(1-DARKING_SPEED));
		int blue = (int)(color.getBlue()*(1-DARKING_SPEED));
		setColor(new Color(red, green, blue));
	}
}