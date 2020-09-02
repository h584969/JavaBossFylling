package h584969.no.hvl.dat100.oppgave5;

import javax.swing.JOptionPane;

public class Karakterskala {
	public static void mainKarakterskala(String[] args) {
		JOptionPane.showMessageDialog(null, "Oppgave 5");
		for (int i = 1; i < 11; i++) {
			int poengsum;

			/*======= Oppgave c =======*/
			do{
				poengsum = Integer.parseInt(JOptionPane.showInputDialog("poengsum for student nr" + i));
				
				if (poengsum < 0 || poengsum > 100) {
				
					JOptionPane.showMessageDialog(null, "Feil. poengsum må være i rekkevidden: 0(inklusiv) <-> 100(inklusiv)");
				}
				
			}while (poengsum < 0 || poengsum > 100);
			/*=========================*/

			char karakter = 'X';
			if (poengsum <= 39) {
				karakter = 'F';
			}
			else if (poengsum <= 49) {
				karakter = 'E';
			}
			else if (poengsum <= 59) {
				karakter = 'D';
			}
			else if (poengsum <= 79) {
				karakter = 'C';
			}
			else if (poengsum <= 89) {
				karakter = 'B';
			}
			else if (poengsum <= 100) {
				karakter = 'A';
			}
			JOptionPane.showMessageDialog(null, "student nr" + i +" fikk karakteren " + karakter);
		}
	}
}
