import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 3, 2018.
 */
public class Node {
	int capacity;
	List<Region> r = new ArrayList<Region>();
	List<Integer> init_r = new ArrayList<Integer>();
	
	public Node(int c, List<Integer> r_space) {
		System.out.println("Create node");
		capacity = c;
		r.add(new Region(r_space.get(0), r_space.get(1), r_space.get(2), r_space.get(3), capacity));
	}
	
	
	public Region findRegionForPoint(Point p) {
		for (int i=0; i<r.size(); i++) {
			if(r.get(i).RegionOverlaps(p)) return r.get(i);
		}
		return null;
	}
	
}
