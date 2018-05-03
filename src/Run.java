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
		
		String l = "25 52 164 240 834";
		Point p = new Point(l);
		System.out.println(p.getPoint());
	}

}
