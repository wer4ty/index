import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 3, 2018.
 */
public class Point {
	List<Integer> coords = new ArrayList<Integer>();
	int dimension;
	
	public Point(String line) {
		String[] s = line.split("\\s+");
		dimension = s.length;
		for (int i=0; i < s.length; i++) {
			coords.add(Integer.parseInt(s[i]));
		}
	   }
	
	public int getDimension() { return dimension; }
	public void setDimension(int d) { dimension = d; }
	public String getPoint() 
	{ return "Coords: "+coords.toString()+" D: "+dimension; }
}
