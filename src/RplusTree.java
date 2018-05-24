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
	public int allPoints = 0;
	
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
	      allPoints = id;
	      return results;
	  }
	
	
	
	
	private List<Point> reOrder(List<Point> pList) {
		Point tmp = new Point(1,1,1);
		for (int i=0; i<pList.size(); i++) {
			for (int j=0; j<i; j++) {
				
				if (pList.get(j).getX() > pList.get(i).getX()) {
					tmp = pList.get(j);
					pList.set(j, pList.get(i));
					pList.set(i, tmp);
				}
				
				
				if (pList.get(j).getY() > pList.get(i).getY()) {
					tmp = pList.get(j);
					pList.set(j, pList.get(i));
					pList.set(i, tmp);
				}
			}
		}
		return pList;
	}
	
	public Node load(File f) {
		try {
			List <Point> rl = readLines(f);
			int minX=0, minY=0, maxX=0, maxY=0;
			
			for(int i=0; i<rl.size(); i++)  {
				if (maxX < rl.get(i).getX()) maxX = rl.get(i).getX();
				if (maxY < rl.get(i).getY()) maxY = rl.get(i).getY();
			}
			System.out.println(minX+" "+minY+" "+maxX+" "+maxY);
			rl = reOrder(rl);
			
			System.out.println("TotalPoint="+allPoints+" MaxReg="+RplusTree.maxRegionsInNode+" MaxPointInReg="+RplusTree.maxPointsInRegion);
			
			
			int bottom_regions_num = (int)Math.ceil(allPoints /(float)RplusTree.maxPointsInRegion);
			
			int pointAdded = 0;
			List<Region> bottom_region = new ArrayList<Region>();
			for (int i=0; i<bottom_regions_num; i++) {
				Region tmp_region = new Region(RplusTree.maxPointsInRegion);
				while (!tmp_region.isFull() && pointAdded < rl.size()) {
					tmp_region.insert(rl.get(pointAdded));
					pointAdded++;
				}
				tmp_region.resize();
				bottom_region.add(tmp_region);
			}
			
			
			int Nodes_num = (int)Math.ceil(bottom_regions_num /(float)RplusTree.maxRegionsInNode);
			int regionsAdded = 0;
			List<Node> bottomLevel = new ArrayList<Node>();
			for (int i=0; i<Nodes_num; i++) {
				Node tmp_node = new Node(RplusTree.maxRegionsInNode);
				while(!tmp_node.isFull() && regionsAdded < bottom_region.size()) {
					tmp_node.insert(bottom_region.get(regionsAdded));
					regionsAdded++;
				}
				bottomLevel.add(tmp_node);
			}
			
			
			// bild bottom up tree
			List<Node> currentLevel = bottomLevel;
			List<Node> upperLevel = new ArrayList<Node>();
			while (Nodes_num > 1) {
				
				regionsAdded = 0;
				for (int i=0; i<Nodes_num; i++) {
					
					Node tmp_node = new Node(RplusTree.maxRegionsInNode);
					while(!tmp_node.isFull() && regionsAdded < Nodes_num){
						
						Region tmp_inside_region = new Region(currentLevel.get(regionsAdded).expand(), RplusTree.maxRegionsInNode);
						tmp_node.insert(tmp_inside_region);
						tmp_node.insertChild(new NodeChild(tmp_inside_region, currentLevel.get(regionsAdded)));
						regionsAdded++;
					}
					
						upperLevel.add(tmp_node);
				}
				
				
				
				currentLevel = upperLevel;
				upperLevel = new ArrayList<Node>();
				Nodes_num = (int)Math.ceil(Nodes_num /(float)RplusTree.maxRegionsInNode);
				
			}
			
			// bild root	
			regionsAdded = 0;
			Node bild_root = new Node(RplusTree.maxRegionsInNode);
			while(!bild_root.isFull() && regionsAdded < Nodes_num){
				Region tmp_inside_region = new Region(currentLevel.get(regionsAdded).expand(), RplusTree.maxRegionsInNode);
				bild_root.insert(tmp_inside_region);
				bild_root.insertChild(new NodeChild(tmp_inside_region, currentLevel.get(regionsAdded)));
				regionsAdded++;
			}
			return bild_root;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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