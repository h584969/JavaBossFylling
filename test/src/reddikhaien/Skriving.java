package reddikhaien;

import java.io.IOException;
import java.io.PrintWriter;

public class Skriving {
	
	public class Kontakt{
		public String getNavn() {
			return "";
		}
		public int getKontaktnummer() {
			return 0;
		}
		
		public Kontakt(String navn, int kontaktnummer) {
			
		}
	}
	
	// ========== Arrangement.java ==========
	// ======================================

	public class Arrangement{
	  private String navn;
	  private Kontakt[] deltakere;
	  private int antall;
	  
	  public Arrangement(String navn){
	    this.navn = navn;
	    
	    deltakere = new Kontakt[50];
	    antall = 0;
	    
	  }
	  
	  public boolean leggTilKontakt(Kontakt k){
	    
	    if (antall < deltakere.length){
	      deltakere[antall++] = k;
	      
	      return true;
	    }
	    
	    return false;
	  }
	  
	  public boolean finnes(String navn, int nummer){
	    
	    for (int i = 0; i < antall; i++){
	      
	      if (deltakere[i].getNavn().equals(navn) && 
	          deltakere[i].getKontaktnummer() == nummer ) return true;
	    }
	    
	    return false;
	  }
	  
	  public boolean registrer(String navn, int nummer){

	    if ( finnes(navn,nummer) ) return false;
	    
	    return leggTilKontakt(new Kontakt(navn, nummer));

	  }
	  
	  public boolean erRegistrert(String navn){
		    
		    for (int i = 0; i < antall; i++){
		      
		      if (deltakere[i].getNavn().equals(navn) ) return true;
		    }
		    
		    return false;
		  }
	  
	  public int[] hentKontaktnumre(){
		    
		    int[] numre = new int[antall];
		    
		    for (int i = 0; i < antall; i++){
		      
		      numre[i] = deltakere[i].getKontaktnummer();
		    }
		    
		    return numre;
		  }
		}


		// ======== SmitteSproring.java =========
		// ======================================

		public static class SmitteSporing{
		  
		  public static boolean skalSpores(Arrangement b, String navn){
		    
		    return b.erRegistrert(navn);
		  }
		  
		  public static void skrivUtKontakter(Arrangement[] arrangementer, String navn){
		    for (Arrangement a : arrangementer){
		      if (a.erRegistrert(navn)){
		        int[] telefonnumre = a.hentKontaktnumre();
		        
		        //printer ut numrene til alle på arrangementet uten å printe nummeret til personen som testet positivt
		        for (int nummer : telefonnumre){
		          if (!a.finnes(navn,nummer) ) System.out.println(nummer);
		        }
		        
		      }
		    }
		  }
		}
		
		//======================= Vare.java =====================
		//=======================================================

		public static abstract class Vare{
		  private String navn;
		  private int nummer;
		  private double pris;
		  
		  public Vare(String navn, int nummer, double pris){
		    this.navn = navn;
		    this.nummer = nummer;
		    this.pris = pris;
		  }
		  
		  public String getNavn(){
		    return navn;
		  }
		  public void setNavn(String navn){
		    this.navn = navn;
		  }
		  
		  public int getNummer(){
		    return nummer;
		  }
		  public void setNummer(int nummer){
		    this.nummer = nummer;
		  }
		  
		  public double getPris(){
		    return pris;
		  }
		  public void setPris(double pris){
		    this.pris = pris;
		  }
		  
		  @Override
		  public String toString(){
		    return navn+"\nVarenummer:"+nummer+"\nPris:"+pris+"\n";
		  }
		  
		  
		  public static boolean lagre(Vare[] varer, String filnavn){
		    boolean skrevet = true;
		    
		    
		    try{
		      
		      //lager et nytt skriveobjekt
		      PrintWriter writer = new PrintWriter(filnavn);
		    
		    
		      //går gjennom hvert element i listen og printer det til skriveobjektet
		      for (final Vare vare : varer){
		        
		        writer.println(vare.toString());
		        
		      }
		      
		      //lukkeren skriveren
		      writer.close();
		    
		    }catch(IOException e){
		      System.err.println("FEIL. Fikk et IOunntak:");
		      
		      e.printStackTrace();
		      
		      skrevet = false;
		    }
		    
		    
		    return skrevet;
		  }
		  
		  
		}

		//=================== DigitalVare.java ==================
		//=======================================================


		public class DigitalVare extends Vare{
		  private String url;
		  
		  
		  public DigitalVare(String navn, int nummer, double pris, String url){
		    super(navn,nummer,pris);
		    this.url = url;
		  }
		  
		  public String getURL(){
		    return url;
		  }
		  public void setURL(String url){
		    this.url = url;
		  }
		  
		  @Override
		  public String toString(){
		    return super.toString() + "Download:"+url+"\n";
		  }
		}

		//=================== FysiskVare.java ===================
		//=======================================================

		public class FysiskVare extends Vare{
		  private int antall;
		  
		  public FysiskVare(String navn, int nummer, double pris, int antall){
		    super(navn,nummer,pris);
		    this.antall = antall;
		  }
		  
		  public int getAntall(){
		      return antall;
		  }
		  public void setAntall(int antall){
		    this.antall = antall;
		  }
		  
		  @Override
		  public String toString(){
		    String tilgjengelig = "";
		    if (antall > 50){
		      tilgjengelig = "50+ tilgjengelig\ns";
		    }
		    else if (antall < 50 && antall > 0){
		      tilgjengelig = antall + " tilgjengelig\n";
		    }
		    else{
		      tilgjengelig = "ikke tilgjengelig\n";
		    }
		    return super.toString() + tilgjengelig;
		  }
		}
		
		
		// ========== Kalender.java =========
		// ==================================

		public class Kalender {
		  
		  private String[][] ukekalender;
		  
		  private final static int DAGER = 5;
		  
		  private final static int TIDSPUNKTER = 4;
		  
		  public Kalender() {
		    ukekalender = new String[TIDSPUNKTER][DAGER];
		  }
		  
		  public void noter(int dag, int tid, String str){
		    
		    ukekalender[tid][dag] = str;
		  }
		  
		  
		  public String hentNotat(int dag, int tid){
		    
		    //tolket oppgaven slik at en tom streng ikke regnes som at noe er notert og dermed returnerer null.
		    if (ukekalender[tid][dag] == null || ukekalender[tid][dag].equals("") ) return null;
		    
		    return ukekalender[tid][dag];
		  }
		  
		  public void slett(int dag, int tid){
		    ukekalender[tid][dag] = null;
		  }
		  
		  public boolean harNotat(int dag, int tid){
		    if ( ukekalender[tid][dag] == null || ukekalender[tid][dag].equals("") ) return false;
		    
		    return true;
		  }
		  
		  public void leggTil(int dag, int tid, String str){
		    if (harNotat(dag,tid)){
		      ukekalender[tid][dag] += str;
		    }
		    else{
		      noter(dag,tid,str);
		    }
		  }
		  
		  public void skrivUtTidspunkt(int tid){
		    String ut = tid+"||\t";
		    
		    for (int i = 0; i < DAGER; i++){
		      String notat = hentNotat(i,tid);
		      
		      if (notat == null){
		        ut += "-|\t";
		      }
		      else{
		        ut += notat+"|\t";
		      }
		    }
		    
		    System.out.println(ut);
		  }
		  
		  public void skrivUt(){
		    
		    for (int i = 0; i < TIDSPUNKTER; i++){
		      
		      skrivUtTidspunkt(i);
		    }
		  }
		}

	
	public static void main(String[] args) {
		
	}
}
