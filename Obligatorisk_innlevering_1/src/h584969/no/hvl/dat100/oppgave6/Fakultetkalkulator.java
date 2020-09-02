package h584969.no.hvl.dat100.oppgave6;

import javax.swing.JOptionPane;

public class Fakultetkalkulator {
	
	public static void mainFakultetKalkulator(String[] args ) {
			JOptionPane.showMessageDialog(null, "Oppgave 6");
			
			int n = Integer.parseInt(JOptionPane.showInputDialog("gi et positivt heltall"));
			
			if (n < 0) {
				JOptionPane.showMessageDialog(null, "Tallet må være over 0");
			}
			
			int sum = 1;
			
			for (int i = 1; i <= n; i++) {
				sum *= i;
			}
			
			JOptionPane.showMessageDialog(null, n + "! = " + sum);
	}
}
