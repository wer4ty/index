import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 3, 2018.
 */
public class Node {
	int regions_num = 4;
	List<Region> r = new ArrayList<Region>();
	
	public Node() {
		addRegion();
	}
	
	public void addRegion() {
		if (regions_num > 0) {
			r.add(new Region());
			regions_num--;
		}
		else {
			System.out.println("Cannot add region into current node need split");
		}
	}
}
