package reddikhaien.oppg2;

public class Oppgave2 {
	public static class Kontakt{
		private String n;
		private int kn;
		public Kontakt(String navn, int nummer) {
			n = navn;
			kn = nummer;
		}
		
		public String getNavn() {
			return n;
		}
		
		public int getKontaktnummer() {
			return kn;
		}
	}
	// ========== Arrangement.java ==========
	// ======================================

	public static  class Arrangement{
	  private String navn;
	  private Kontakt[] deltakere;
	  private int antall;
	  
	  public Arrangement(String navn){
	    this.navn = navn;
	    
	    deltakere = new Kontakt[50];
	    antall = 0;
	    
	  }
	  
	  public String getNavn() {
		  return navn;
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
	        
	        
	        System.out.println("arrangenment: " + a.getNavn()); 
	        for (int nummer : telefonnumre){
	          if (!a.finnes(navn,nummer) ) System.out.println(nummer);
	        }
	        
	      }
	    }
	  }
	}
	
	public static void main(String[] args) {
		Arrangement a = new Arrangement("LS");
		Arrangement b = new Arrangement("HK");
		Arrangement c = new Arrangement("Julebord");
		
		a.registrer("alf", 1);
		a.registrer("ole", 2);
		a.registrer("konrad", 3);
		a.registrer("knut", 4);
		
		b.registrer("knut", 4);
		b.registrer("trine", 5);
		b.registrer("håvard", 6);
		b.registrer("ole", 2);
	
		c.registrer("knut", 4);
		c.registrer("kfrwfj", 7);
		c.registrer("helle", 8);
		c.registrer("kinsarvik", 9);
		c.registrer("konrad", 3);

		SmitteSporing.skrivUtKontakter(new Arrangement[] {
				a,b,c
		}, "knut");
	}

}
