package reddikhaien;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import easygraphics.*;

public class Main extends EasyGraphics {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void run() {
		makeWindow("kalkulator", 800, 600);
		
		JPanel rot = new JPanel();
		rot.setLayout( new GridLayout(2,2));
		
		JTextField lengde = new JTextField(5);
		JTextField bredde = new JTextField(5);
		JLabel resultattxt = new JLabel("0.0");
		resultattxt.setHorizontalAlignment(SwingConstants.CENTER);
		rot.add(lengde);
		rot.add(bredde);
		rot.add(resultattxt);
		
		lengde.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double dlengde, dbredde;
				dlengde = Double.parseDouble(lengde.getText());
				dbredde = Double.parseDouble(bredde.getText());
				
				resultattxt.setText(Double.toString(dlengde*dbredde));
			}
		});
		
		bredde.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double dlengde, dbredde;
				dlengde = Double.parseDouble(lengde.getText());
				dbredde = Double.parseDouble(bredde.getText());
				
				resultattxt.setText(Double.toString(dlengde*dbredde));
			}
		});
		
		this.getContentPane().add(rot);
	}
	
}
