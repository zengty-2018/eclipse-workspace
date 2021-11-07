import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.*;
import java.awt.Color;

/**
 * UnboundedGrid implement with array
 * @author z18342007
 *
 * @param <E>
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
  private Object[][] occupantArray;
  private int gridSize;
  private final int FACTOR = 2;

	/**
	 * Constructor, the default gridSize is 16.
	 */
	public UnboundedGrid2() {
	  gridSize = 16;
	  occupantArray = new Object[16][16];
	}

	/**
	 * Get row num, infinite, return -1
	 */
  	public int getNumRows()
    {
        return -1;
    }

	/**
	 * Get col num, infinite, return -1
	 */
    public int getNumCols()
    {
        return -1;
    }

    /**
     * Judge whether the given location is valid
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    /**
     * Get all occupied Locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> occupiedLocation = new ArrayList<Location>();
        for (int i = 0; i < gridSize; i++) {
           for (int j = 0; j < gridSize; j++) {
              Location loc = new Location(i, j);
              if (get(loc) != null) {
                 occupiedLocation.add(loc);
              }
           }
        }
        return occupiedLocation;
    }
    
    /**
     * Get the Actor in the given location
     */
    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (loc.getRow() >= gridSize || loc.getCol() >= gridSize)
          return null;
        
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    /**
     * Add a obj to the given location
     */
    public E put(Location loc, E obj) {
      if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is invalid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        int presentSize = gridSize;
        
        while (loc.getRow() >= gridSize || loc.getCol() >= gridSize) {
          gridSize *= FACTOR;
        }

        if (gridSize != presentSize) {
          Object[][] newGrid = new Object[gridSize][gridSize];
          for (int i = 0; i < presentSize; i++) {
            System.arraycopy(occupantArray[i], 0, newGrid[i], 0, presentSize);
          }
          occupantArray = newGrid;
          
        }
        
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
     * Remove the obj in the given Location
     */
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        

        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}