package h584969.engine;

import java.util.Calendar;

public abstract class EntityModule implements Runnable{
	
	private boolean running = true;
	protected int dataRetrievalRate = 1;
	/**
	 * det minste kravet til hvor lenge en update skal vare
	 */
	private long minTickTime = 16L;
	private float fixedDeltaTime = (float)minTickTime/1000.0f;
	synchronized final void termiate() {
		running = false;
		System.out.println("stopper: " + this.getClass().getName());
	}
	
	@Override
	public final void run() {
		long before = 0;
		long after = Calendar.getInstance().getTimeInMillis();
		
		int frameCounter = 0;
		while(running) {
			before = Calendar.getInstance().getTimeInMillis();
			frameCounter++;
			if (frameCounter > dataRetrievalRate) { retrieveData(); frameCounter = 0;}
			update();
			makeDataAviable();
			
			after = Calendar.getInstance().getTimeInMillis();
			
			try {
				//sover den resterende tiden: tickTime - (before - after) eller 1
				Thread.sleep(Math.max(minTickTime - (before - after), 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		terminated();
	}
	
	public abstract void createNewData(long id);
	
	/**
	 * Blir kalt når modulen skal kopiere inn data 
	 */
	protected abstract void retrieveData();

	/**
	 * oppdaterer modulen. blir kalt uregelmessig
	 */
	protected abstract void  update();
	
	public final float getFixedDeltaTime() {
		return fixedDeltaTime;
	}
	/**
	 * blir kalt når update er ferdig. ment for å gjøre den nye dataen fra update tilgjengelig for kopiering
	 */
	protected abstract void makeDataAviable();
	
	/**
	 * Blir kalt når denne modulen blir terminert
	 */
	protected abstract void terminated();
	
	public abstract void sendMessage(IEntityMessage message);
	
	protected final void logInvalidMessageType(String expected, String recieved) {
		System.err.println("Error! Feil meldingsType. forventet " + expected + " men fikk " + recieved);
	}
	
	protected final void setMaxTickRate(long TickRate) {
		minTickTime = TickRate > 0 ? TickRate : 1;
		fixedDeltaTime = minTickTime/1000.0f;
	}
	
}
