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
	
	
	public Node findInternalRegionForPoint(Point p) {
		for (int i=0; i<childs.size(); i++) {
			if(childs.get(i).getRegion().RegionOverlaps(p)) return childs.get(i).getChild();
		}
		return null;
	}
	
	public Region findLeafRegionForPoint(Point p) {
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
	
	public void insertChild(NodeChild n) {
		childs.add(n);
	}
	
	public List<Integer> expand() {
		int _minX = (int)Double.POSITIVE_INFINITY,
			_minY = (int)Double.POSITIVE_INFINITY,
			_maxX = 0,
			_maxY = 0;
		
			int rmin_x, rmin_y, rmax_x, rmax_y;
			for (int i=0; i<r.size(); i++) {
				rmin_x = r.get(i).getMinX();
				rmin_y = r.get(i).getMinY();
				rmax_x = r.get(i).getMaxX();
				rmax_y = r.get(i).getMaxY();
				if (rmin_x < _minX) _minX = rmin_x;
				if (rmax_x > _maxX) _maxX = rmax_x;
				
				if (rmin_y < _minY) _minY = rmin_y;
				if (rmax_y > _maxY) _maxY = rmax_y;
				
			}
			List<Integer> newSizes = new ArrayList<Integer>();
			newSizes.add(_minX);
			newSizes.add(_minY);
			newSizes.add(_maxX);
			newSizes.add(_maxY);
			
			
			return newSizes;
	}
	
	public String toString()  { return "Node: "+r+"\n"+childs+"\n"; }
	
}
