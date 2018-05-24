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
public class Run {

	public static void main(String[] args) throws Exception {
		File dataFile = new File("./resourse/data.dat");
		
		Point p1 = new Point(1, 3,3);
		Point p2 = new Point(2, 4,7);
		Point p3 = new Point(3, 7,2);
		Point p4 = new Point(4, 8,8);
		Point p5 = new Point(5, 6,4);
		
		
		RplusTree t = new RplusTree(2,2);
		System.out.println(t.load(dataFile));
		//t.insert(p1);
				
	}

}
