import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;



public class DeleteDialog extends JDialog implements ActionListener {
	private JTextField  user_select;
	private JLabel result;
	private Vizual frame;
	
		public DeleteDialog(Vizual _frame, String title) {
			frame =  _frame;
			this.setTitle(title);
			JPanel main=new JPanel();
			main.setLayout(new GridLayout(3,1,10,10));
			user_select = new JTextField(10);
			result = new JLabel();
			
				
			JButton b1 = new JButton("Delete");
			b1.addActionListener(this);

			 
			 
			main.add(new JLabel("<html>Type point for delete<br> (only int numbers supports <br>with only space seperator)<br><i>Exemple: <font color='red'><b>85 28</b></font></i> </html>"));
			main.add(user_select);
			main.add(result);
			
				
			this.setSize(250, 400);
			this.getContentPane().add(BorderLayout.CENTER, main);
			this.getContentPane().add(BorderLayout.SOUTH, b1);
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			
			this.setVisible(true);	
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Delete")) {
				
				if (Vizual.tree == null) {
					JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree");
				}
				else {
						String res = Vizual.tree.deletePoint(user_select.getText());
						result.setText(res);
						System.out.println("Delete point result: "+res);
						frame.DrawTree(null, null);
				}
				
			}
			
			
			else if (e.getActionCommand().equals("Print Tree")) {
				
				if (Vizual.tree == null) {
					JOptionPane.showMessageDialog(null, "Tree is not initialized. Before use it, please init tree");
				}
				else {
						Vizual.tree.printTree();
						
				}
				
			}
			
		}

}
