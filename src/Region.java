import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 3, 2018.
 */
public class Region {
	List<Point> data = new ArrayList<Point>();
	Node child;
	int capacity;
	
	// quadratic base
	int min_x, min_y, max_x, max_y;
	
	public Region(List<Integer> coords) {
		System.out.println("Create new bound region");
		child = null;	
	}
	
	public int getMinX() { return min_x; }
	public int getMinY() { return min_y; }
	public int getMaxX() { return max_x; }
	public int getMaxY() { return max_y; }
	
}
