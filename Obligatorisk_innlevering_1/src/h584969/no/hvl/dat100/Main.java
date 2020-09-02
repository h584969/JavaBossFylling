package h584969.no.hvl.dat100;

import h584969.no.hvl.dat100.oppgave4.Trinnskatt;
import h584969.no.hvl.dat100.oppgave5.Karakterskala;
import h584969.no.hvl.dat100.oppgave6.Fakultetkalkulator;

public class Main {
	public static void main(String[] args ) {
		//alle oppgavene ha sin egen klasse
		
		Trinnskatt.mainTrinnskatt(args);
		
		Karakterskala.mainKarakterskala(args);
		
		Fakultetkalkulator.mainFakultetKalkulator(args);
	}
}
