import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;


public class InitTreeDialog extends JDialog implements ActionListener {
	private JTextField  MaxPointsInRegion,MaxRegionInNode;
	private JFileChooser fileChooser;
	private Vizual frame;
	private JButton loadFiletrigger;
	private JLabel fileP;
	
		public InitTreeDialog(Vizual _frame, String title) {
			frame =  _frame;
			this.setTitle(title);
			JPanel main=new JPanel();
			main.setLayout(new GridLayout(4,2,10,10));
			
				
			
			loadFiletrigger = new JButton("Load File");
			MaxPointsInRegion = new JTextField(20);
			MaxPointsInRegion.setText(String.valueOf(RplusTree.maxPointsInRegion));
			MaxPointsInRegion.setFont(Vizual.font);
			MaxRegionInNode = new JTextField(20);
			MaxRegionInNode.setFont(Vizual.font);
			MaxRegionInNode.setText(String.valueOf(RplusTree.maxRegionsInNode));
			if (RplusTree.filePath != null)
				fileP = new JLabel("File Path: "+RplusTree.filePath);
			else
				fileP = new JLabel("File Path:");
			
			JButton b1 = new JButton("Bild new Tree");
			b1.addActionListener(this);
			loadFiletrigger.addActionListener(this);
			  			  		  
			main.add(new JLabel("Max number of points in region (min 2):"));
			main.add(MaxPointsInRegion);
			
			main.add(new JLabel("Max number of regions in node (min 2):"));
			main.add(MaxRegionInNode);
			main.add(fileP);
			main.add(loadFiletrigger);
			
				
			this.setSize(600, 200);
			this.getContentPane().add(BorderLayout.CENTER, main);
			this.getContentPane().add(BorderLayout.SOUTH, b1);
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			
			this.setVisible(true);	
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Bild new Tree")) {
				
				if (!MaxPointsInRegion.getText().isEmpty())
					if(Integer.parseInt(MaxPointsInRegion.getText()) >= 2) 
						RplusTree.maxPointsInRegion = Integer.parseInt(MaxPointsInRegion.getText());
					else
						RplusTree.maxPointsInRegion = 2;
				if (!MaxRegionInNode.getText().isEmpty())
					if(Integer.parseInt(MaxRegionInNode.getText()) >= 2)
						RplusTree.maxRegionsInNode = Integer.parseInt(MaxRegionInNode.getText());
					else
						RplusTree.maxRegionsInNode = 2;
				
				// init tree
				if (RplusTree.maxPointsInRegion <= 0 || RplusTree.maxRegionsInNode <= 0 || RplusTree.filePath == null) {
					JOptionPane.showMessageDialog(null, "Init parametrs are wrong\nPlease try again set parametrs");
				}
				
				
				else {
					System.out.println(RplusTree.maxPointsInRegion+"  "+RplusTree.maxRegionsInNode+" "+RplusTree.filePath);
					long startTime = System.nanoTime();
					Vizual.tree = new RplusTree(RplusTree.maxPointsInRegion,RplusTree.maxRegionsInNode);
					Vizual.tree.load(RplusTree.filePath);
					frame.DrawTree(null, null);
					long stopTime = System.nanoTime();
					double seconds = (double)(stopTime - startTime) / 1000000000.0;
					System.out.printf("Load time: %.2f seconds\n\n", seconds);
					this.dispose();
				}
								
			}
			else if (e.getActionCommand().equals("Load File")) {
				fileChooser = new JFileChooser(".");
				fileChooser.setPreferredSize(new Dimension(500,500));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					fileP.setText(selectedFile.getAbsolutePath());
					RplusTree.filePath = selectedFile.getAbsolutePath();
				}
			}
		}

}
