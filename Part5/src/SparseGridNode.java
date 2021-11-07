
/**
 * The class that implement the node.
 * @author z18342007
 *
 */
public class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;

    /**
     * Constructor with the node value and col num parameters.
     * @param obj: value of this location
     * @param col: the col number 
     */
    public SparseGridNode(Object obj, int col)
    {
        occupant = obj;
        this.col = col;
        next = null;
    }

    /**
     * Get the node value
     * @return
     */
    public Object getOccupant()
    {
        return occupant;
    } 

    /**
     * Get the column number
     * @return
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Get the next node
     * @return
     */
    public SparseGridNode getNextNode()
    {
        return next;
    }

    /**
     * Delete the next node
     */
    public void clearNext()
    {
        this.next = null;
    }

    /**
     * Delete the given col value
     * @param col
     * @return
     */
    public Object removeNode(int col)
    {
        SparseGridNode p = this;
        while(p.next != null && p.next.getCol() != col)
        {
            p = p.next;
        }
        if(p.next == null)
        {
            return null;
        }else
        {
            Object obj = p.next.getOccupant();
            p.next = null;
            return obj;
        }
    }

    /**
     * add the given col value
     * @param obj
     * @param col
     */
    public void addNode(Object obj, int col)
    {
        SparseGridNode p = this;
        while(p.getNextNode() != null)
        {
            p = p.getNextNode();
        }
        p.next = new SparseGridNode(obj, col);
    }
}