package reddikhaien.ai.task;

public abstract class Task {
	
	
	/**
	 * kalt for � sjekke om oppgaven skal gjennomf�res
	 * @return om denne handlingen skal gjennomf�res
	 */
	public abstract boolean shouldExecute();
	
	/**
	 * Returnerer sann om oppgaven skal fortsette � gjennomf�res etter at {@link #startExecute()} er blitt kalt
	 * 
	 * @return sann om den skal fortsette usann ellers
	 */
	public boolean shouldCountinue() { return false; }
	
	/**
	 * blir kalt n�r handlingen starter
	 */
	public void startExecute() {}
	
	/**
	 * blir kalt s� lenge {@link #shouldCountinue()} returnerer sann 
	 * (om {@link #shouldCountinue()} aldri blir sann vil bare {@link #startExecute()} bli gjennomf�rt
	 * n�r {@link #shouldExecute()} blir sann
	 */
	public void execute() {}
	
	/**
	 * blir gjennomf�rt n�r oppgaven fullf�rer eller blir avbrutt av en h�yere prioritert oppgave
	 */
	public void restart() {}
}
