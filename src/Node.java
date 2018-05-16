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
	
	public Node(int c) {
		System.out.println("Create node");
		capacity = c;
	}
}
