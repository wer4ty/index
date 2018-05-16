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
	
	public RplusTree(int n1, int n2) {
		System.out.println("Init Rplus Tree");
		root = new Node(maxRegionsInNode);
		
		maxPointsInRegion = n1;
		maxRegionsInNode = n2;
	}
}
