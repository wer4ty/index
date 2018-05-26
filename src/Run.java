import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.File;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Server.
 *         Created May 1, 2018.
 */
public class Run {


	
	public static void main(String[] args) throws Exception {
		long startTime = System.nanoTime();
		
//		Point p1 = new Point(1, 3,3);
//		Point p2 = new Point(2, 4,7);
//		Point p3 = new Point(3, 7,2);
//		Point p4 = new Point(4, 8,8);
//		Point p5 = new Point(5, 6,4);
		
		RplusTree t = new RplusTree(3,3);
		t.load("./resourse/do_data.dat");
		//System.out.println();
		//System.out.println("Point search: "+t.selectPoint("78 147 538 634 738"));
		System.out.println("Region search: "+t.selectRegionOfPoints("2 15 2 39"));
		
		long stopTime = System.nanoTime();
		double seconds = (double)(stopTime - startTime) / 1000000000.0;
		System.out.printf("%.2f seconds\n\n", seconds);
			
//		 File htmlFile = new File("visualization/index.html");
//		 Desktop.getDesktop().browse(htmlFile.toURI());
//		
//		final ServerSocket server = new ServerSocket(8181);
//	    System.out.println("Listening for connection on port 8181 ....");
//	    while (true){
//	    	 try (Socket socket = server.accept()) {
//	                Date today = new Date();
//	                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
//	                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
//	            }
//	    }
	}

}