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
	
	public RplusTree() {
		System.out.println("Init Rplus Tree");
		root = new Node();
	}
	
	public void insert(Point p) {
		System.out.println("\t add point "+p.toString());
		root.insertToNode(p);
	}
}
