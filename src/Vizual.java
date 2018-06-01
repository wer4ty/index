import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;  
import javax.swing.text.Document;



public class Vizual extends JFrame implements ActionListener {
	private JPanel main;
	private JEditorPane editorpane;
	public static  String filePath = "visual/index.html";
	public static  String emptyPath = "visual/empty.html";
	public static RplusTree tree;
	
	
	public Vizual() {
		this.setTitle("Visualization R+Tree Index");
		
		// main panel
		main = new JPanel();
		// absolute position of inside elemenths
		main.setLayout(new GridLayout(1,1,10,10) );
		main.setBounds(0, 0, 1000, 800);
		main.setBackground(Color.WHITE);
		
		
		try {
		
			
			 editorpane= new JEditorPane();
			 JScrollPane editorScrollPane = new JScrollPane(editorpane);
		       editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		       editorpane.setPage(new File(emptyPath).toURI().toURL());
		       editorpane.setEditable(false);
		       editorScrollPane.setBounds(0, 0, 1000, 800);
			
			
			main.add(editorScrollPane);
			
			
		} catch (IOException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}
		
		// menu
		JMenuBar menubar = new JMenuBar();
			JMenu file = new JMenu("Main");
				JMenuItem prTr = new JMenuItem("Random Tree");
				JMenuItem exit_m = new JMenuItem("Exit");
				file.add(prTr);
				file.add(exit_m);
			menubar.add(file);
			
			JMenu qr = new JMenu("Queries");
				JMenuItem it = new JMenuItem("Init Tree"); qr.add(it);
				qr.add(new JSeparator());
				JMenuItem sp = new JMenuItem("Select Point"); qr.add(sp);
				JMenuItem sr = new JMenuItem("Select Range"); qr.add(sr);
				qr.add(new JSeparator());
				JMenuItem ip = new JMenuItem("Insert Point"); qr.add(ip); qr.add(new JSeparator());
				JMenuItem dp = new JMenuItem("Delete Point"); qr.add(dp);
			menubar.add(qr);
			
			JMenu help = new JMenu("Help");
				JMenuItem help_m = new JMenuItem("Help");
				help.add(help_m);
			menubar.add(help);
		this.setJMenuBar(menubar);
		
		
		// menu actions
		it.addActionListener(this);
		sp.addActionListener(this);
		sr.addActionListener(this);
		ip.addActionListener(this);
		dp.addActionListener(this);
		exit_m.addActionListener(this);
		help_m.addActionListener(this);
		prTr.addActionListener(this);
		
		
		// submenu panel
		JPanel subMenu = new JPanel();
		subMenu.setLayout(new GridLayout(1, 0));
		
		
		JButton[] Btn = new JButton[8];
		Btn[0] = new JButton("Init Tree");
		Btn[1] = new JButton("Select Point");
		Btn[2] = new JButton("Select Range");
		Btn[3] = new JButton("Delete Point");
		Btn[4] = new JButton("Insert Point");
		Btn[5] = new JButton("Random Tree");
		Btn[6] = new JButton("Help");
		Btn[7] = new JButton("Exit");
		
		for (JButton b : Btn) {
		   subMenu.add(BorderLayout.SOUTH, b);
		}
		
		
		// submenu actions
		Btn[0].addActionListener(this);
		Btn[1].addActionListener(this);
		Btn[2].addActionListener(this);
		Btn[3].addActionListener(this);
		Btn[4].addActionListener(this);
		Btn[5].addActionListener(this);
		Btn[6].addActionListener(this);
		Btn[7].addActionListener(this);
		
		// set into frame two panels
		this.getContentPane().add(BorderLayout.CENTER, main);
		this.getContentPane().add(BorderLayout.SOUTH, subMenu);
		
		// default close action
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,1000);
			
		// center of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
				
		// resize false and make visible
		setResizable(true);
		this.setVisible(true);
	}
	
	
	// actions
		/**
		 * actionPerformed interraction with user GUI
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("Exit")) {
				System.exit(0);
			}
			
			else if (e.getActionCommand().equals("Init Tree")) {
				InitTreeDialog dialog = new InitTreeDialog(this, "Create new R+Tree");
				dialog.setVisible(true);
				dialog.requestFocus();	
			}
			
			else if (e.getActionCommand().equals("Select Point")) {
				SelectDialog dialog = new SelectDialog(this, "Select Point", true);
				dialog.setVisible(true);
				dialog.requestFocus();
			}
			
			else if (e.getActionCommand().equals("Select Range")) {
				SelectDialog dialog = new SelectDialog(this, "Select Range", false);
				dialog.setVisible(true);
				dialog.requestFocus();
			}
			
			else if (e.getActionCommand().equals("Delete Point")) {
				DeleteDialog dialog = new DeleteDialog(this, "Delete Point");
				dialog.setVisible(true);
				dialog.requestFocus();
			}
			
			else if (e.getActionCommand().equals("Insert Point")) {
				//FoodDialog();
			}
			
			else if (e.getActionCommand().equals("Random Tree")) {
				RplusTree.maxPointsInRegion  = ThreadLocalRandom.current().nextInt(2, 6 + 1);
				RplusTree.maxRegionsInNode = ThreadLocalRandom.current().nextInt(2, 6 + 1);
				RplusTree.filePath = "resourse/do_data.dat";
				Vizual.tree = new RplusTree(RplusTree.maxPointsInRegion,RplusTree.maxRegionsInNode);
				Vizual.tree.load(RplusTree.filePath);
				DrawTree();
			}
			
			else if (e.getActionCommand().equals("Help")) { 
			JOptionPane.showMessageDialog(null, "R+Tree Index\nGUI @ 338057227 33574811");
			}
		}
	
		public String randColor(int level) {
			String[] array = {"red", "orange", "green", "blue",
					 "maroon", "lime", "navy", "black", "aqua",
					"purple", "olive" };
			if (level > array.length) level = level % array.length;
			    return array[level];
		}
		
		public void recursiveVisualGenerator(Node node, StringBuilder res, int l) {
			if (node.isLeaf()) {
				List<Region> current_node_regions = node.getRegions();
				res.append("<ul>");
				for(int i=0; i<current_node_regions.size(); i++) {
					res.append("<li style='border: 1px dashed black'><u>"+current_node_regions.get(i).toString()+"</u>");
					
					res.append("<div class='dataPoint'><i><b>{ ");
					List<Point> dataPoints = current_node_regions.get(i).getPoints();
					for (int j=0; j<dataPoints.size(); j++) {
						res.append(dataPoints.get(j).toString()+"  ");
					}
					res.append(" }</i></b></div>");
					
					res.append("</li>");
				}
				res.append("</ul>");
			}
			else {
				List<NodeChild> nc = node.getChilds();
				String color = randColor(l);
				for(int i=0; i<nc.size(); i++) {
					res.append("<ul>");
					res.append("<li style='border: 1px solid "+color+"'><u>"+nc.get(i).getRegion().toString()+"</u>");
					recursiveVisualGenerator(nc.get(i).getChild(), res, ++l);
					res.append("</li>");
					res.append("</ul>");
				}
			}
		}	
		
	
	public void DrawTree() {
		String top = "<!DOCTYPE html><html><head><title>R+Tree index</title><style>ul, li {list-style-type: none;  margin: 0; padding: 0; }ul { padding-left: 20px; border: 0px solid #333; display: block; margin: 5px;  }li { padding-left: 20px; padding-top: 5px; margin-top:5px; display: block; margin-top: 10px; font-weight: bold;} .highlight { background-color: yellow; } .dataPoint {padding-left: 20px; font-size: 10px; font-color: gray; }</style></head><body><img  src='logo.png'>";	
		String bottom = "</body></html>";
		
		StringBuilder tree_representation = new StringBuilder();
		
		
		if (Vizual.tree == null) {
			JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree");
		}
		else {
			Node tmp = Vizual.tree.get();
			recursiveVisualGenerator(tmp, tree_representation, 0);
			
			 
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(Vizual.filePath))) {
				bw.write(top);
				bw.write(tree_representation.toString());
				bw.write(bottom);
				
				//bw.close();

				System.out.println("Done");
				
				   Document doc = editorpane.getDocument();
				   doc.putProperty(Document.StreamDescriptionProperty, null);
				   editorpane.setPage(new File(filePath).toURI().toURL());
				
			
			} catch (IOException e) {

				e.printStackTrace();

			}
			
			
		}
	}
	
}