import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
 * TODO Put here a description of what this class does.
 *
 *         Created May 1, 2018.
 */
public class RplusTree {
	Node root;
	public static int maxPointsInRegion = 0;
	public static int maxRegionsInNode  = 0;
	public static String filePath = null;
	public int allPoints = 0;
	public static List<String> orig_points;
	public String dataFile;
	
	// quadratic base min_x, min_y, max_x, max_y
	List<Integer> space = new ArrayList<Integer>();
	
	public RplusTree(int n1, int n2) {	
		space.add(0);
		space.add(0);
		space.add((int)Double.POSITIVE_INFINITY);
		space.add((int)Double.POSITIVE_INFINITY);

		root = new Node(maxRegionsInNode);
		orig_points = new ArrayList<String>();
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
	    	  orig_points.add(line);
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
	
	

	
	public String selectPoint(String line) {
		Point p = new Point(-1, line);
		Node tmp = root;
		if (tmp != null) {
			while (!tmp.isLeaf()) {
				Node t = tmp.findInternalRegionForPoint(p);
				if (t != null) tmp = t;
				else return "Not Found";
			}
			Region goal = tmp.findLeafRegionForPoint(p);
			if (goal != null) {
				int rs = goal.search(p);
				if(rs != -1) {
					return orig_points.get(rs);
				}
			}
		}
		return "Not Found";
	}
	
	private void filterDownToleafs(Node node, List<Region> res, Region sw) {
		if (node.isLeaf()) {
			List<Region> rc = node.getRegions();
			for(int i=0; i<rc.size(); i++) {
				if(rc.get(i).RegionOverlaps(sw)) {
					res.add(rc.get(i)); 
				}
			}

		}
		else {
			List<NodeChild> nc = node.getChilds();
			for(int i=0; i<nc.size(); i++) {
				if(nc.get(i).getRegion().RegionOverlaps(sw)) {
					filterDownToleafs(nc.get(i).getChild(), res, sw);
				}
			}
		}
	}
	
	public String selectRegionOfPoints(String line) {
		List<String> res = new ArrayList<String>();
		
		// bild search window
		String[] s = line.split("\\s+");
		if (s.length != 4) { return "Wrong format List have to be [minX, minY, maxX, maxY]"; }
		else {
			List<Integer> search_bounds = new ArrayList<Integer>();
			for (int i=0; i < s.length; i++) {
				search_bounds.add(Integer.parseInt(s[i]));
			}
			
			Region search_window = new Region(search_bounds, RplusTree.maxPointsInRegion);
			List<Region> leafs_regions = new ArrayList<Region>();
			Node tmp = root;
					
			filterDownToleafs(tmp,leafs_regions, search_window);
			
			for(int i=0; i<leafs_regions.size(); i++) {
				List<Point> pointsfromReg = leafs_regions.get(i).getPoints();
					for(int j=0; j<pointsfromReg.size(); j++) {
						if(search_window.RegionOverlaps(pointsfromReg.get(j))) {
							res.add(orig_points.get(pointsfromReg.get(j).getId()));
						}
					}
			}
		}
		if(res.size() > 0) return res.toString();
		else return "Not Found";
	}
	
	public Node load(String fPath) {
		try {
			dataFile = fPath;
			File f = new File(fPath);
			List <Point> rl = readLines(f);
			int minX=0, minY=0, maxX=0, maxY=0;
			
			for(int i=0; i<rl.size(); i++)  {
				if (maxX < rl.get(i).getX()) maxX = rl.get(i).getX();
				if (maxY < rl.get(i).getY()) maxY = rl.get(i).getY();
			}
			//System.out.println(minX+" "+minY+" "+maxX+" "+maxY);
			rl = reOrder(rl);
			
			System.out.println("TotalPoint="+allPoints+" MaxRegInNode="+RplusTree.maxRegionsInNode+" MaxPointInReg="+RplusTree.maxPointsInRegion);
			
			
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
						currentLevel.get(regionsAdded).setParent(tmp_node);
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
			root = bild_root;
			return bild_root;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	private void reExpandTree(Node node, List<Region> res) {
		if (node.isLeaf()) {
			for(int i=0; i<node.getRegions().size(); i++) {
				node.getRegions().get(i).resize();
			}	
		}
		else {
			List<NodeChild> nc = node.getChilds();
			for(int i=0; i<nc.size(); i++) {
				Node current_child = nc.get(i).getChild();
				reExpandTree(current_child, res);
				
				// resize internal nodes
				Region current_region = nc.get(i).getRegion();
				int _minX =(int)Double.POSITIVE_INFINITY, _minY = (int)Double.POSITIVE_INFINITY, _maxX = 0, _maxY = 0;
				List<Region> reg_of_child = current_child.getRegions();
				for(int k=0; k<reg_of_child.size(); k++) {
					Region inR = reg_of_child.get(k);
					if (inR.getMinX() < _minX) _minX = inR.getMinX();
					if (inR.getMinY() < _minY) _minY = inR.getMinY();
					if (inR.getMaxX() > _maxX) _maxX = inR.getMaxX();
					if (inR.getMaxY() > _maxY) _maxY = inR.getMaxY();
				}
				current_region.setMinX(_minX);
				current_region.setMinY(_minY);
				current_region.setMaxX(_maxX);
				current_region.setMaxY(_maxY);
			}
		}
	}
	
	public String deletePoint(String line) {
		Point p = new Point(-1, line);
		Node tmp = root;
		if (tmp != null) {
			while (!tmp.isLeaf()) {
				Node t = tmp.findInternalRegionForPoint(p);
				if (t != null) tmp = t;
				else return "Not Found";
			}
			Region goal = tmp.findLeafRegionForPoint(p);
			if (goal != null) {
				int rs = goal.searchIndex(p);
				if(rs != -1) {
					goal.removePoint(rs); // remove point
					
					// if region is empty remove it
					if (goal.isEmpty()) {
						tmp.removeRegion(goal);
						
						// if node is empty remove node and remove in parent nodechild and region
							while(tmp != null ) {
								if(tmp.isEmpty()) {
									Node t = tmp;
									tmp = tmp.getParent();
									List<NodeChild> nc = tmp.getChilds();
									for(int i=0; i<nc.size(); i++) {
										if(nc.get(i).getChild() == t) {
											nc.remove(i);
											tmp.getRegions().remove(i);
										}
									}
								}
								else { break; }
							}
					}
					reExpandTree(root, root.getRegions()); // change mbr parametrs
					return "Succesfully deleted";
				}
			}
		}
		return "Not Found";
	}
	
	public void printTree() {
		System.out.println(root);
	}
	
	
	private void findNodeForNewPoint(Node node, Point p, Node res) {
		if (node.isLeaf()) {
			List<Region> current_node_regions = node.getRegions();
			for(int i=0; i<current_node_regions.size(); i++) {
				if(current_node_regions.get(i).RegionOverlaps(p)) {
					res = node;
					return;
				}
					
				else
					break;
			}
		}
		else {
			List<NodeChild> nc = node.getChilds();
			for(int i=0; i<nc.size(); i++) {
				if(nc.get(i).getRegion().RegionOverlaps(p)) {
					findNodeForNewPoint(nc.get(i).getChild(), p, res);
				}
			}
		}
	}

	public void insert(String line) {
		Node tmp = null;
		RplusTree.orig_points.add(line);
		Point p = new Point(++allPoints, line);
		
		findNodeForNewPoint(root,p, tmp);
		System.out.println("Insert->>>>>>>>>>>>>>");
		System.out.println(tmp);
		
	}
	
	
	
}