package h584969.no.hvl.dat100.oppgave4;

import javax.swing.JOptionPane;

public class Trinnskatt {
	public static void mainTrinnskatt(String[] args ) {
	 
		JOptionPane.showMessageDialog(null, "Oppgave 4");
		int bruttoInntekt = Integer.parseInt(JOptionPane.showInputDialog("brutto"));
		double trinnskatt = 0.0;
		if (bruttoInntekt <= 180_800) {
			JOptionPane.showMessageDialog(null, bruttoInntekt + "kr brutto gir ingen trinnskatt");
			return;
		}
		else if (bruttoInntekt > 180_800 && bruttoInntekt <= 254_500) {
			trinnskatt = 1.9;
		}
		else if (bruttoInntekt > 254_500 && bruttoInntekt <= 639_750) {
			trinnskatt = 4.2;
		}
		else if (bruttoInntekt > 639_750 && bruttoInntekt <= 999_550) {
			trinnskatt = 13.2;
		}
		else{
			trinnskatt = 16.2;
		}
		
		int skattet = (int)(bruttoInntekt*trinnskatt/100.0);
		int netto = bruttoInntekt - skattet;
		JOptionPane.showMessageDialog(null, bruttoInntekt + "kr brutto gir " + skattet + "kr(" + trinnskatt + "%) i trinnskatt.\nNetto utbetalt blir: " + netto + "kr");
	}

}
