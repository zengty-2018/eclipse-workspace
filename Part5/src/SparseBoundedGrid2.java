import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * BoundedGrid implement by hash map
 * @author z18342007
 *
 * @param <T>
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> hashMap;
    private int row;
    private int col;

    /**
     * Constructor
     * @param rows
     * @param cols
     */
    public SparseBoundedGrid2(int row, int col) {
        hashMap = new HashMap<Location, E>();
        this.row = row;
        this.col = col;
    }

    /**
     * Get the row num
     */
    public int getNumRows() {
        return row;
    }

    /**
     * Get the col num
     */
    public int getNumCols() {
        return col;
    }

    /**
     *  Judge whether the location is vaild
     */
    public boolean isValid(Location loc) {
        int r = loc.getRow();
        int c = loc.getCol();
        if ((r >= 0) && (r < getNumRows()) && (c >= 0) && (c <= getNumCols())) {
            return true;
        }
        return false;
    }

    /**
     * Get all occupied locations
     */
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location loc : hashMap.keySet()) {
            locs.add(loc);
        }
        return locs;
    }

    /**
     * Get the Actor in the given location
     */
    public E get(Location loc) {
        if (loc == null) {
            throw new IllegalArgumentException("Error! Location: " + loc + " is inValid.");
        }
        return hashMap.get(loc);
    }

    /**
     * Add a obj to the given location
     */
    public E put(Location loc, E obj) {
        if (loc == null) {
            throw new IllegalArgumentException("Error! Location: " + loc + " is inValid.");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        return hashMap.put(loc, obj);
    }

    /**
     * Remove the obj in the given Location
     */
    public E remove(Location loc) {
        if (loc == null) {
            throw new IllegalArgumentException("Error! Location: " + loc + " is InValid.");
        }
        return hashMap.remove(loc);
    }
}