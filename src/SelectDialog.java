import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;



public class SelectDialog extends JDialog implements ActionListener {
	private JTextField  user_select;
	private boolean pointSearch;
	private Vizual frame;
	private JLabel result;
	
		public SelectDialog(Vizual _frame, String title, boolean point) {
			frame =  _frame;
			this.setTitle(title);
			JPanel main=new JPanel();
			main.setLayout(new GridLayout(4,1,10,10));
			pointSearch = point;
			user_select = new JTextField(10);
			result = new JLabel();
			
				
			JButton b1 = new JButton("Select");
			b1.addActionListener(this);

			 
			if (pointSearch) 
				main.add(new JLabel("<html>Type select point<br> (only int numbers supports <br>with only space seperator)<br><i>Exemple: <font color='red'><b>85 28</b></font></i> </html>"));
			else
				main.add(new JLabel("<html>Type select region (between two points) <br>Format: [point1, point2]<br><i>Exemple: <font color='red'><b>10 5, 100 200</b></font></i>"
						+ "<br><i>Exemple2: <font color='red'><b>34 85 18 49, 100 200 315 280</b></font></i></html>"));
			main.add(user_select);
			main.add(result);
				
			this.setSize(350, 400);
			this.getContentPane().add(BorderLayout.CENTER, main);
			this.getContentPane().add(BorderLayout.SOUTH, b1);
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			
			this.setVisible(true);	
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Select")) {
				
				if (Vizual.tree == null) {
					JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree");
				}
				else {
					ArrayList<Region> way = new ArrayList<Region>();
					if(pointSearch) {
						String res = Vizual.tree.selectPoint(user_select.getText(), way);
						result.setText("<html>"+res+"</html>");
						if (res.trim().equals(user_select.getText().trim())) {
							frame.DrawTree(way, new Point(-1, res));
						}
						System.out.println("Point search result: "+res);
					}
					else {
						String res = Vizual.tree.selectRegionOfPoints(user_select.getText(), way);
						result.setText("<html>"+res+"</html>");
						frame.DrawTree(way, null);
						System.out.println("Region search result: "+res);
					}
					
				}				
			}
		}

}
