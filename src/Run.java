/**
 * TODO Put here a description of what this class does.
 *
 *         Created May 1, 2018.
 */
public class Run {


	
	public static void main(String[] args) throws Exception {
		long startTime = System.nanoTime();
		

		
		RplusTree t = new RplusTree(3,3);
		
		
		t.load("./resourse/do_data.dat");
		t.printTree();
		//System.out.println();
		
		//System.out.println("Point search: " +t.selectPoint("13 47"));
		

//		t.deletePoint("13 49");
//		t.deletePoint("90 86");
//		t.deletePoint("44 22");
//		t.deletePoint("105 342");
//		t.deletePoint("15 45 67");
//		t.deletePoint("67 86");
//		t.deletePoint("45 45 66");
//		t.deletePoint("300 32");
//		t.deletePoint("78 95");
//		t.deletePoint("40 42");
//		t.deletePoint("15 55");
//		t.deletePoint("34 1");
//		System.out.println("After Delete");
//		t.printTree();
		
		t.insert("84 96");
		
		//System.out.println("Region search: "+t.selectRegionOfPoints("2 10 3 150"));
		
		long stopTime = System.nanoTime();
		double seconds = (double)(stopTime - startTime) / 1000000000.0;
		System.out.printf("%.2f seconds\n\n", seconds);

////////////////////////////////////////////////////////////////////////////////////
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