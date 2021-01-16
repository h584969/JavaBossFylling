package reddikhaien.oppg3;

public class Oppgave3 {
	// ========== Kalender.java =========
	// ==================================

	public static class Kalender {
	  
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
	    String ut = tid+"||";
	    
	    for (int i = 0; i < DAGER; i++){
	      String notat = hentNotat(i,tid);
	      
	      if (notat == null){
	        ut += "         -|";
	      }
	      else{
	        ut += " ".repeat(Math.max(0, 10 - notat.length()))+notat+"|";
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
		Kalender kalender = new Kalender();
		kalender.noter(0, 0, "hei");
		kalender.noter(1, 1, "hade");
		kalender.noter(4, 3, "notat");
		kalender.skrivUt();
	}
}
