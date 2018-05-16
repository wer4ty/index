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
		System.out.println("Init Rplus Tree");
		maxPointsInRegion = n1;
		maxRegionsInNode = n2;
		init_bounds = space;
		
		root = new Node(maxRegionsInNode, init_bounds);
	}
}
