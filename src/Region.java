import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 *         Created May 3, 2018.
 */
public class Region {
	List<Point> data = new ArrayList<Point>();
	int capacity;
	int currentCapacity = 0;
	
	// quadratic base
	int min_x, min_y, max_x, max_y;
	
	public Region(int c) {
		min_x = 0;
		min_y = 0;
		max_x = (int)Double.POSITIVE_INFINITY;
		max_y = (int)Double.POSITIVE_INFINITY;
		capacity = c;	
	}
	
	public Region(int x1, int y1, int x2, int y2, int c) {
		min_x = x1;
		min_y = y1;
		max_x = x2;
		max_y = y2;
		capacity = c;	
	}
	
	public Region(List<Integer> intSpace, int c) {
		min_x = intSpace.get(0);
		min_y = intSpace.get(1);
		max_x = intSpace.get(2);
		max_y = intSpace.get(3);
		capacity = c;	
	}

	public List<Point> getPoints() { return data; }
	
	public boolean RegionOverlaps(Point p) {
		int px = p.getX();
		int py = p.getY();
		//System.out.println("P("+px+", "+py+") R{"+min_x+", "+min_y+", "+max_x+", "+max_y+"}");
			if (min_x <= px 
				&& px <= max_x 
				&& min_y <= py 
				&& py <= max_y )
			return true;
		return false;
	}
	
	public boolean RegionOverlaps(Region sw) {
		int rminx = this.getMinX(), 
				rminy = this.getMinY(), 
				rmaxx = this.getMaxX(),
				rmaxy = this.getMaxY(),
			rHeight = rmaxy - rminy,
			rWidth = rmaxx - rminx,
			swrminX = sw.getMinX(),
			swrminY = sw.getMinY(),
			swrmaxX = sw.getMaxX(),
			swrmaxY = sw.getMaxY(),
			swHeight = swrmaxY - swrminY,
			swWidth = swrmaxX - swrminX;
		
		return !(swrminX > rmaxx ||  swrminY > rmaxy || rminx > swrmaxX || rminy > swrmaxY);
}
		
	public void insert(Point p) {
		data.add(p);
	}
	
	public int search(Point p) {
		for (int i=0; i<data.size(); i++) {
			if (p.getX() == data.get(i).getX()
				&& 	
				p.getY() == data.get(i).getY()) {
				return data.get(i).getId();
			}
		}
		return -1;
	}
	
	public int searchIndex(Point p) {
		for (int i=0; i<data.size(); i++) {
			if (p.getX() == data.get(i).getX()
				&& 	
				p.getY() == data.get(i).getY()) {
				return i;
			}
		}
		return -1;
	}
	
	public void resize() {
			int _minX = (int)Double.POSITIVE_INFINITY,
				_minY = (int)Double.POSITIVE_INFINITY,
				_maxX = 0,
				_maxY = 0;
		
			int px, py;
			for (int i=0; i<data.size(); i++) {
				px = data.get(i).getX();
				py = data.get(i).getY();
				if (px < _minX) _minX = px;
				if (px > _maxX) _maxX = px;
				
				if (py < _minY) _minY = py;
				if (py > _maxY) _maxY = py;
				
			}

			min_x = _minX;
			min_y = _minY;
			max_x = _maxX;
			max_y = _maxY;
		}
	
	public void resize(List<Integer> newSpace) {
		min_x = newSpace.get(0);
		min_y = newSpace.get(1);
		max_x = newSpace.get(2);
		max_y = newSpace.get(3);
	}
	

	public void removePoint(int index) {
		data.remove(data.get(index));
		currentCapacity--;
	}
	
	public void clear() {
		data.clear();
		currentCapacity = 0;
	}
	
	public boolean isEmpty() { if(data.size() == 0) return true; else return false; }
	
	public int getMinX() { return min_x; }
	public int getMinY() { return min_y; }
	public int getMaxX() { return max_x; }
	public int getMaxY() { return max_y; }
	public int getCapacity() { return capacity; }
	
	
	public void setMinX(int _c) {  min_x = _c; }
	public void setMinY(int _c) {  min_y  = _c; }
	public void setMaxX(int _c) {  max_x  = _c; }
	public void setMaxY(int _c) {  max_y  = _c; }
	public void setCapacity(int _c) {  capacity = _c; }
	


	public boolean isFull() {
		if(data.size() >= capacity)  return true;  else return false; 
		}
	
	public String toString()  { return "\n\t\tR: ("+min_x+", "+min_y+", "+max_x+", "+max_y+") == {"+data+"}"; }
	//public String toString()  { return "\n\t\tR: == {"+data+"\t}"; }
	
	public void displayPoints() {
		System.out.println(this);
		for (int i=0; i<data.size(); i++) {
			System.out.print(data.get(i)+", ");
		}
		System.out.println();
	}
}
