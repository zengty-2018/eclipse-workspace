import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

/**
 * Running BlusterCritter.
 * @author z18342007
 *
 */
public final class BlusterCritterRunner{   
    public static void main(String[] args){
		ActorWorld world = new ActorWorld();
		for(int i = 0; i < 10; i++) {
			world.add(new Location(i, i), new BlusterCritter());			
		}
		world.show();
    }
}