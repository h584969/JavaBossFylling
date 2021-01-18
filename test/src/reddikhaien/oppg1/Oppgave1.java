package reddikhaien.oppg1;

import java.io.IOException;
import java.io.PrintWriter;

public class Oppgave1 {
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


	public static class DigitalVare extends Vare{
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

	public static class FysiskVare extends Vare{
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
	      tilgjengelig = "50+ tilgjengelig\n";
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

	
	public static void main(String[] args) {
		Vare[] varer = new Vare[3];
		varer[0] = new FysiskVare("ost", 55, 57.0, 65);
		varer[1] = new FysiskVare("motorsykker", 46, 1000101.45, 2);
		varer[2] = new FysiskVare("meningen til livet",3484 , 0.50, 0);
		
		Vare.lagre(varer, "hei.txt");
	}
}
