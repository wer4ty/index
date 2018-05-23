import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 1, 2018.
 */
public class RplusTree {
	Node root;
	public static int maxPointsInRegion;
	public static int maxRegionsInNode;
	
	// quadratic base min_x, min_y, max_x, max_y
	List<Integer> space = new ArrayList<Integer>();
	
	public RplusTree(int n1, int n2) {
		maxPointsInRegion = n1;
		maxRegionsInNode = n2;
		
		space.add(0);
		space.add(0);
		space.add((int)Double.POSITIVE_INFINITY);
		space.add((int)Double.POSITIVE_INFINITY);

		root = new Node(maxRegionsInNode, space);
	}
	
	public Node get() { return root; }
	
	public void insert(Point p) {
		// find leaf
		int px = p.getX(), py = p.getY();
		Node tmp = root;
		while (! tmp.isLeaf() ) {
			List<NodeChild> ch = tmp.getChilds();
			for(int i=0; i<ch.size(); i++) {
				NodeChild nc = ch.get(i);
				if (nc.getRegion().RegionOverlaps(p)) {
					tmp = nc.getChild();
					break;
				}
			}
		}
		// find good region in this node 
		Region reg_goal = tmp.findRegionForPoint(p);
		
		if (reg_goal != null && !(reg_goal.isFull()) ) {
				reg_goal.insert(p); 
		}
		
		// create new in current node
		else {
			if(!(tmp.isFull())) { 
		}
		
	}
		
	}
	
	
	
}