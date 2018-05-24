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
	List<NodeChild> childs = new ArrayList<NodeChild>();
	
	public Node(int c) {
		capacity = c;
	}
	
	public Node(int c, List<Integer> space) {
		if (c < 3) c = 3;
		capacity = c;
		r.add(new Region(space.get(0),space.get(1), space.get(0)/2, space.get(1)/2,  capacity));
	}
	
	
	public Region findRegionForPoint(Point p) {
		for (int i=0; i<r.size(); i++) {
			if(r.get(i).RegionOverlaps(p)) return r.get(i);
		}
		return null;
	}
	
	public boolean isFull() { if(r.size() >= capacity)  return true;  else return false; } 
	

	public List<Region> getRegions() { return r; }
	public List<NodeChild> getChilds() { return childs; }
	public boolean isLeaf() {  if(childs.size() == 0) return true; else return false; }

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param region
	 */
	public void insert(Region region) {
		r.add(region);
	}
	
	public String toString()  { return "Node: ["+r+"]\n"; }
	
}
