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
	
	public Region(int x1, int y1, int x2, int y2, int c) {
		System.out.println("Create new region");
		child = null;	
	}
	
	public boolean RegionOverlaps(Point p) {
		int px = p.getX();
		int py = p.getY();
		
		if (min_x <= px && px < max_x && min_y <= py && py < max_y )
			return true;
		return false;
	}
	
	public int getMinX() { return min_x; }
	public int getMinY() { return min_y; }
	public int getMaxX() { return max_x; }
	public int getMaxY() { return max_y; }
	
}
