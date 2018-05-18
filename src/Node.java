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
		capacity = c;
		r.add(new Region(r_space.get(0), r_space.get(1), r_space.get(2), r_space.get(3), capacity));
	}
	
	
	public Region findRegionForPoint(Point p) {
		for (int i=0; i<r.size(); i++) {
			
			if(r.get(i).RegionOverlaps(p)) return r.get(i);
		}
		return null;
	}
	
	public List<Region> getRegions() { return r; }
	
	public void displayNode() {
		
	}
		
	public Node split(Point p, Region mbr) {
		// if it is possible to add new region in current node
		if(r.size() < capacity) {
			// take all points + new point 
			List<Point> tmp = new  ArrayList<Point>();
			List<Point> fromRegions = new  ArrayList<Point>();
			for (int i=0; i<r.size(); i++) {
				fromRegions = r.get(i).getPoints();
				for(int j=0; j<fromRegions.size(); j++) {
					tmp.add(fromRegions.get(j));
				}
			}
			tmp.add(p);
			
			// clear all point in current region
			mbr.clear();
			
			// divide current region into two sub regions
			int x1,x2,y1,y2;
			x1 = mbr.getMinX();
			x2 = mbr.getMaxX();
			y1 = mbr.getMinY();
			y2 = mbr.getMaxY();
			
			mbr.setMaxX(x1+x2/2);
			
			r.add(new Region(x1+x2/2, y1, x2, y2, RplusTree.maxPointsInRegion));
			Region mbr2 = r.get(r.size()-1); // just created
			
			for (int i=0; i<tmp.size(); i++) {
				if (mbr.RegionOverlaps(tmp.get(i)) && !(mbr.isFull()) ) {
					mbr.insert(tmp.get(i));
				}
				else if(mbr2.RegionOverlaps(tmp.get(i)) && !(mbr2.isFull()) ) {
					mbr2.insert(tmp.get(i));
				}
				else {
					continue;
				}
			}
			 
			return this;  // didn't change tree structure
		}
		
		// if current node is full therefore need recusively split parents nodes
		else {
			return this;
		}
	}
	
}
