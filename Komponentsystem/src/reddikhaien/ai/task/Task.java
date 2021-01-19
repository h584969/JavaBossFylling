package reddikhaien.ai.task;

public abstract class Task {
	
	
	/**
	 * kalt for å sjekke om oppgaven skal gjennomføres
	 * @return om denne handlingen skal gjennomføres
	 */
	public abstract boolean shouldExecute();
	
	/**
	 * Returnerer sann om oppgaven skal fortsette å gjennomføres etter at {@link #startExecute()} er blitt kalt
	 * 
	 * @return sann om den skal fortsette usann ellers
	 */
	public boolean shouldCountinue() { return false; }
	
	/**
	 * blir kalt når handlingen starter
	 */
	public void startExecute() {}
	
	/**
	 * blir kalt så lenge {@link #shouldCountinue()} returnerer sann 
	 * (om {@link #shouldCountinue()} aldri blir sann vil bare {@link #startExecute()} bli gjennomført
	 * når {@link #shouldExecute()} blir sann
	 */
	public void execute() {}
	
	/**
	 * blir gjennomført når oppgaven fullfører eller blir avbrutt av en høyere prioritert oppgave
	 */
	public void restart() {}
}
