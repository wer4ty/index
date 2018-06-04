import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO Put here a description of what this class does.
 *
 *         Created May 1, 2018.
 */
public class Run  {
	
	public static void main(String[] args) throws Exception {
		
		//	Vizual win = new Vizual();
		
		RplusTree.maxPointsInRegion  = 3;
		RplusTree.maxRegionsInNode = 3;
		RplusTree t = new RplusTree(3,3);
		RplusTree.filePath = "resourse/do_data.dat";
		t.load(RplusTree.filePath);
		t.printTree();
		
		t.insert("50 50");
		t.printTree();

		
//		List<Integer> myList = new ArrayList<Integer>();
//		myList.add(0);
//		myList.add(2);
//		myList.add(4);
//		myList.add(1, 3);
//		System.out.println(myList);
	}
}