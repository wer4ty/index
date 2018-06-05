/**
 * TODO Put here a description of what this class does.
 *
 *         Created May 3, 2018.
 */
public class Point {
	int id;
	int x, y;
	double equality_factor;
	
	public Point(int _id, int _x, int _y) {
		id = _id;
		x = _x;
		y = _y;
		equality_factor = 0.0;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getId() { return id; }
	
	public Point(int _id, String line) {
		line = line.trim();
		String[] s = line.split("\\s+");
		id = _id;
		int tmp_sum = 0, sqr_sum = 0, tmp_val =0;
		for (int i=0; i < s.length; i++) {
			tmp_val = Integer.parseInt(s[i]);
			tmp_sum += tmp_val;
			sqr_sum += tmp_val * tmp_val;
		}
		y = tmp_sum / s.length;  // average of all numbers
		x = (int) Math.sqrt(sqr_sum); // vector presentation
		equality_factor = Math.sqrt(sqr_sum); // to check two point equals
	   }
 
	
	public boolean equals(Point obj) {
	    if (obj == null) {
	        return false;
	    }
	    
	    
	    //if(this.getX() == obj.getX() && this.getY() == obj.getY())
	    if(this.equality_factor == obj.equality_factor)
	    	return true;
	    return false;
	}
	
	public int squareBetween(Point p) {
		int height = Math.abs(p.getY() - getY());
		int width = Math.abs(p.getX() - getX());
		return height * width;
	}
	
	public String toString() { return "\tP ["+RplusTree.orig_points.get(id)+"]"; }

}
