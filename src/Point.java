import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 3, 2018.
 */
public class Point {
	int id;
	int x, y;
	
	public Point(int _id, int _x, int _y) {
		id = _id;
		x = _x;
		y = _y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getId() { return id; }
	
	public Point(int _id, String line) {
		String[] s = line.split("\\s+");
		id = _id;
		x = s.length;		
		int tmp_sum = 0;
		for (int i=0; i < s.length; i++) {
			tmp_sum += Integer.parseInt(s[i]);
		}
		y = tmp_sum / x;
	   }


	public String toString() { return "P [ID: "+id+": ("+x+", "+y+")]"; }

}
