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

	
	public static List<String> readLines(File file) throws Exception {
		int countLines = 0;
	      if (!file.exists()) {
	          return new ArrayList<String>();
	      }
	      BufferedReader reader = new BufferedReader(new FileReader(file));
	      List<String> results = new ArrayList<String>();
	      String line = reader.readLine();
	      while (line != null) {
	          results.add(line);
	          line = reader.readLine();
	          countLines++;
	      }
	      System.out.println(countLines);
	      return results;
	  }
	
	public static void main(String[] args) throws Exception {
		//File dataFile = new File("./resourse/data.dat");
		//readLines(dataFile);
		
		Point p1 = new Point(1, 3,3);
		Point p2 = new Point(2, 4,7);
		Point p3 = new Point(3, 5,2);
		Point p4 = new Point(4, 3,8);
		Point p5 = new Point(5, 6,2);
		Point p6 = new Point(6, 7,7);
		
		RplusTree t = new RplusTree(3,3);
		
	}

}
