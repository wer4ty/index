import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;  



public class Vizual extends JFrame implements ActionListener {
	private JPanel main;
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
		
			
			 JEditorPane editorpane= new JEditorPane();
			 JScrollPane editorScrollPane = new JScrollPane(editorpane);
		       editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		       File file = new File("visual/index.html");
		       editorpane.setPage(file.toURI().toURL());
		       editorpane.setEditable(false);
		       editorpane.setBounds(0, 0, 1000, 800);
			
			
			main.add(editorpane);
			
			
		} catch (IOException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}
		
		// menu
		JMenuBar menubar = new JMenuBar();
			JMenu file = new JMenu("Main");
				JMenuItem prTr = new JMenuItem("Print Tree");
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
		Btn[5] = new JButton("Print Tree");
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
		setResizable(false);
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
			
			else if (e.getActionCommand().equals("Print Tree")) {
				if (Vizual.tree != null)
					Vizual.tree.printTree();
				else
					JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree");
			}
			
			else if (e.getActionCommand().equals("Help")) { 
			JOptionPane.showMessageDialog(null, "R+Tree Index\nGUI @ 338057227 33574811");
			}
		}
	
	
	
}