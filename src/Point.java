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
	
//	public Point(String line) {
//		String[] s = line.split("\\s+");
//		dimension = s.length;
//		for (int i=0; i < s.length; i++) {
//			coords.add(Integer.parseInt(s[i]));
//		}
//	   }
//	
//	public List<Integer> getCoords() {
//		return coords;
//	}
//	
//	public int getDimension() { return dimension; }
//	public void setDimension(int d) { dimension = d; }
//	public String getPoint() 
//	{ return "Coords: "+coords.toString()+" D: "+dimension; }
//	public String toString() 
//	{ return "Coords: "+coords.toString()+" D: "+dimension; }
}
