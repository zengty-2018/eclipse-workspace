import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

/**
 * BoundedGrid implement by link list
 * @author z18342007
 *
 * @param <T>
 */
public class SparseBoundedGrid<T> extends AbstractGrid<T> {
    private int maxCols;
    private SparseGridNode[] occupantArray;
    
    /**
     * Constructor
     * @param rows
     * @param cols
     */
    public SparseBoundedGrid(int rows, int cols){
        if (rows <= 0){
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
        occupantArray = new SparseGridNode[rows];
        maxCols = cols;
    }

    /**
     * Get the row num
     */
    public int getNumRows(){
        return occupantArray.length;
    }

    /**
     * get the col num
     */
    public int getNumCols(){
        return maxCols;
    }

    /**
     * Judge whether the Location is valid
     */
    public boolean isValid(Location loc){
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    /**
     * Get all occupied Locations
     */
    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> occupiedLocation = new ArrayList<Location>();

        for (int r = 0; r < getNumRows(); r++){
            SparseGridNode p = occupantArray[r];

            if(p == null){
                continue;
            }
            
            while(p != null){
                occupiedLocation.add(new Location(r, p.getCol()));
                p = p.getNextNode();
            }
        }
        return occupiedLocation;
    }

    /**
     * Get the value of given Location
     */
    public T get(Location loc){
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is  invalid");
        }
        SparseGridNode p = occupantArray[loc.getRow()];

        if(p == null){
            return null;
        }
        while(p != null && p.getCol() != loc.getCol()){
            p = p.getNextNode();
        }
        if(p == null){
            return null;
        }
        return (T) p.getOccupant(); 
    }

    /**
     * Add a obj to the given location
     */
    public T put(Location loc, T obj){
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is invalid");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }

        T oldOccupant = get(loc);
        SparseGridNode p = occupantArray[loc.getRow()];
        if(p == null){
            occupantArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol());
        }else{
            occupantArray[loc.getRow()].addNode(obj, loc.getCol());
        }
        return oldOccupant;
    }

    /**
     * Remove the obj in the given Location
     */
    public T remove(Location loc){
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is invalid");
        }
        
        int col = loc.getCol(), row = loc.getRow();
        SparseGridNode p = occupantArray[row];
        Object obj;

        if(p.getCol() != col){
            obj = p.removeNode(col);
        }else{
            obj = p.getOccupant();
            occupantArray[row] = null;
        }
        return (T) obj;
    }
}