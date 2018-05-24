import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 1, 2018.
 */
public class RplusTree {
	Node root;
	public static int maxPointsInRegion;
	public static int maxRegionsInNode;
	
	// quadratic base min_x, min_y, max_x, max_y
	List<Integer> space = new ArrayList<Integer>();
	
	public RplusTree(int n1, int n2) {
		maxPointsInRegion = n1;
		maxRegionsInNode = n2;
		
		space.add(0);
		space.add(0);
		space.add((int)Double.POSITIVE_INFINITY);
		space.add((int)Double.POSITIVE_INFINITY);

		root = new Node(maxRegionsInNode, space);
	}
	
	public Node get() { return root; }
	
	private  List<Point> readLines(File file) throws Exception {
		int id = 0;
	      if (!file.exists()) {
	          return new ArrayList<Point>();
	      }
	      BufferedReader reader = new BufferedReader(new FileReader(file));
	      List<Point> results = new ArrayList<Point>();
	      String line = reader.readLine();
	      while (line != null) {
	          results.add(new Point(id, line));
	          line = reader.readLine();
	          id++;
	      }
	      return results;
	  }
	private List<Point> reOrder(List<Point> pList) {
		Point tmp = new Point(1,1,1);
		for (int i=0; i<pList.size(); i++) {
			System.out.println(pList.get(i));
		}
		return null;
	}
	
	public void load(File f) {
		try {
			List <Point> rl = readLines(f);
			int minX=0, minY=0, maxX=0, maxY=0;
			for(int i=0; i<rl.size(); i++)  {
				System.out.println(rl.get(i));
				if (maxX < rl.get(i).getX()) maxX = rl.get(i).getX();
				if (maxY < rl.get(i).getY()) maxY = rl.get(i).getY();
			}
			System.out.println(minX+" "+minY+" "+maxX+" "+maxY);
			reOrder(rl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(Point p) {
		// find leaf
		int px = p.getX(), py = p.getY();
		Node tmp = root;
		while (! tmp.isLeaf() ) {
			List<NodeChild> ch = tmp.getChilds();
			for(int i=0; i<ch.size(); i++) {
				NodeChild nc = ch.get(i);
				if (nc.getRegion().RegionOverlaps(p)) {
					tmp = nc.getChild();
					break;
				}
			}
		}
		// find good region in this node 
		Region reg_goal = tmp.findRegionForPoint(p);
		
		if (reg_goal != null && !(reg_goal.isFull()) ) {
				reg_goal.insert(p); 
		}
		
		// create new in current node
		else {
			if(!(tmp.isFull())) { 
		}
		
	}
		
	}
	
	
	
}