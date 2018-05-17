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
	int maxPointsInRegion;
	int maxRegionsInNode;
	
	List<Integer> init_bounds = new ArrayList<Integer>();
	
	public RplusTree(int n1, int n2, List<Integer> space) {
		maxPointsInRegion = n1;
		maxRegionsInNode = n2;
		init_bounds = space;
		root = new Node(maxRegionsInNode, init_bounds);
	}
	
	public Node getRoot() { return root; }
	
	public void insert(Node node, Point p) {
		Region r_out = node.findRegionForPoint(p);
		if (r_out == null) 
			System.out.println("Point not in tree space");
		else {
			// insert into region if it in child node 
			if(r_out.getChild() == null) {
				// and not reach capacity of points
				if( !(r_out.isFull()) ) {
					r_out.insert(p);
				}
				// reach capacity split current node and his region into sub-regions
				else {
					r_out.split();
				}
			}
			
			// if it's not leaf recursivly downward to leaf
			else 
				insert(r_out.getChild(), p);
			}	
	}
	
	
}