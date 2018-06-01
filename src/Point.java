/**
 * TODO Put here a description of what this class does.
 *
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
		line = line.trim();
		String[] s = line.split("\\s+");
		id = _id;
		x = s.length;		
		int tmp_sum = 0;
		for (int i=0; i < s.length; i++) {
			tmp_sum += Integer.parseInt(s[i]);
		}
		y = tmp_sum / x;
	   }
 
	
	public boolean equals(Point obj) {
	    if (obj == null) {
	        return false;
	    }
	    if(this.getX() == obj.getX() && this.getY() == obj.getY())
	    	return true;
	    return false;
	}
	
	//public String toString() { return "\n\t\t\tP [ID: "+id+": ("+x+", "+y+")]"; }
	public String toString() { return "\tP ["+RplusTree.orig_points.get(id)+"]"; }

}
