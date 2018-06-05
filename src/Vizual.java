import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;  
import javax.swing.text.Document;



public class Vizual extends JFrame implements ActionListener {
	private JPanel main;
	private JEditorPane editorpane;	
	public static  String filePath = "visual/index.html";
	public static  String emptyPath = "visual/empty.html";
	public static Font font = new Font("SansSerif", Font.BOLD, 20);
	public static RplusTree tree;
		
	public Vizual() {
		this.setTitle("Visualization R+Tree Index");
		
		// main panel
		main = new JPanel();
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
		
		
		// menu actions
		it.addActionListener(this);
		sp.addActionListener(this);
		sr.addActionListener(this);
		ip.addActionListener(this);
		dp.addActionListener(this);
		exit_m.addActionListener(this);
		prTr.addActionListener(this);
		
		
		// submenu panel
		JPanel subMenu = new JPanel();
		subMenu.setLayout(new GridLayout(1, 0));
		
		
		JButton[] Btn = new JButton[7];
		Btn[0] = new JButton("Init Tree");
		Btn[1] = new JButton("Select Point");
		Btn[2] = new JButton("Select Range");
		Btn[3] = new JButton("Delete Point");
		Btn[4] = new JButton("Insert Point");
		Btn[5] = new JButton("Random Tree");
		Btn[6] = new JButton("Exit");
		
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
				if (Vizual.tree != null) {
				SelectDialog dialog = new SelectDialog(this, "Select Point", true);
				dialog.setVisible(true);
				dialog.requestFocus();
				}
				else
					JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else if (e.getActionCommand().equals("Select Range")) {
				if (Vizual.tree != null) {
				SelectDialog dialog = new SelectDialog(this, "Select Range", false);
				dialog.setVisible(true);
				dialog.requestFocus();
				}
				else
					JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else if (e.getActionCommand().equals("Delete Point")) {
				if (Vizual.tree != null) {
				DeleteDialog dialog = new DeleteDialog(this, "Delete Point");
				dialog.setVisible(true);
				dialog.requestFocus();
				}
				else
					JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else if (e.getActionCommand().equals("Insert Point")) {
				if (Vizual.tree != null) {
					InsertDialog dialog = new InsertDialog(this, "Insert new Point");
					dialog.setVisible(true);
					dialog.requestFocus();
				}
				else
					JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else if (e.getActionCommand().equals("Random Tree")) {
				RplusTree.maxPointsInRegion  = ThreadLocalRandom.current().nextInt(2, 6 + 1);
				RplusTree.maxRegionsInNode = ThreadLocalRandom.current().nextInt(2, 6 + 1);
				RplusTree.filePath = "data/20_points_data.dat";
				Vizual.tree = new RplusTree(RplusTree.maxPointsInRegion,RplusTree.maxRegionsInNode);
				Vizual.tree.load(RplusTree.filePath);
				DrawTree(null, null);
			}
		}
	
		public String randColor(int level) {
			String[] array = {"orange", "blue",
					 "maroon", "lime", "navy", "black", "aqua",
					"purple", "olive" };
			if (level > array.length) level = level % array.length;
			    return array[level];
		}
		
		public void recursiveVisualGenerator(Node node, StringBuilder res, int l, List<Region> path, Point search_point) {
			int border_size = 1;
			if (node.isLeaf()) {
				List<Region> current_node_regions = node.getRegions();
				res.append("<ul>");
				
				boolean partOfPath = false;
				for(int i=0; i<current_node_regions.size(); i++) {
					
					if(path != null) {
						for (int k=0; k<path.size(); k++) {
							if(current_node_regions.get(i) == path.get(k)) {
								partOfPath = true;
								break;
							}
							else 
								partOfPath = false;
						}
					}
					
					
					String toAppend;
					if(path != null && partOfPath)
						toAppend = "<li style='border: "+border_size+"px dashed black'><u><h3 class='highlight'>"+current_node_regions.get(i).toString()+"</h3></u>";
					else
						toAppend = "<li style='border: "+border_size+"px dashed black'><u>"+current_node_regions.get(i).toString()+"</u>";
					
					res.append(toAppend);
					res.append("<div class='dataPoint'><i><b>{ ");
					List<Point> dataPoints = current_node_regions.get(i).getPoints();
					for (int j=0; j<dataPoints.size(); j++) {
						
						String pointAppend = dataPoints.get(j).toString()+"  ";
						if (search_point != null) {
							if (search_point.equals(dataPoints.get(j)))
								pointAppend = "<span class='highlight'>"+dataPoints.get(j).toString()+"</span>  ";
						}
						
						res.append(pointAppend);
					}
					res.append(" }</i></b></div>");
					
					res.append("</li>");
				}
				res.append("</ul>");
			}
			else {
				List<NodeChild> nc = node.getChilds();
				String color = randColor(l);
				boolean partOfPath = false;
				for(int i=0; i<nc.size(); i++) {
					
					if(path != null) {
						for (int k=0; k<path.size(); k++) {
							if(nc.get(i).getRegion() == path.get(k)) {
								partOfPath = true;
								
								break;
							}
							else 
								partOfPath = false;
						}
					}
					
					String toAppend;
					if(path != null && partOfPath)
						toAppend = "<li style='border: "+border_size+"px solid "+color+"'><u><h3 class='highlight'>"+nc.get(i).getRegion().toString()+"</h3></u>";
					else
						toAppend = "<li style='border: "+border_size+"px solid "+color+"'><u>"+nc.get(i).getRegion().toString()+"</u>";
					 
					
					res.append("<ul>");
					res.append(toAppend);
					recursiveVisualGenerator(nc.get(i).getChild(), res, ++l, path, search_point);
					res.append("</li>");
					res.append("</ul>");
				}
			}
		}	
		
	
	public void DrawTree(List<Region> path, Point p) {
		String top = "<!DOCTYPE html><html><head><title>R+Tree index</title><style>ul, li {list-style-type: none;  margin: 0; padding: 0; }ul { padding-left: 20px; border: 0px solid #333; display: block; margin: 5px;  }li { padding-left: 20px; padding-top: 5px; margin-top:5px; display: block; margin-top: 10px; font-weight: bold;} .highlight { background-color: yellow; display:inline; border: 1px solid black; } .dataPoint {padding-left: 20px; font-size: 10px; font-color: gray; } .tree { border:3px solid green; }</style></head><body><img  src='logo.png'><div class='tree'><h2 style='margin-left:10px;'> ROOT</h2>";	
		String bottom = "</div></body></html>";
		
		StringBuilder tree_representation = new StringBuilder();
		
		
		if (Vizual.tree == null) {
			JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree");
		}
		else {
			Node tmp = Vizual.tree.get();
			recursiveVisualGenerator(tmp, tree_representation, 0, path, p);
						 
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