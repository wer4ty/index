import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO Put here a description of what this class does.
 *
 *         Created May 1, 2018.
 */
public class Run  {
	
	public static void main(String[] args) throws Exception {
		
			//Vizual win = new Vizual();
		
		RplusTree.maxPointsInRegion  = 3;
		RplusTree.maxRegionsInNode = 3;
		RplusTree t = new RplusTree(3,3);
		RplusTree.filePath = "resourse/do_data.dat";
		t.load(RplusTree.filePath);
		t.printTree();
		
		t.insert("105 342");
	}
}