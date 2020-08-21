package h584969.engine;
import h584969.engine.data.packet.DataPacket;
import h584969.engine.data.packet.IPacket;

public abstract class EntityModule<T extends IPacket<T>> implements Runnable{
	
	private boolean running = true;
	protected int dataRetrievalRate = 1;
	
	
	synchronized void termiate() {
		running = false;
		System.out.println("stopper: " + this.getClass().getName());
	}
	
	@Override
	public void run() {
		int frameCounter = 0;
		while(running) {
			frameCounter++;
			if (frameCounter > dataRetrievalRate) { retrieveData(); frameCounter = 0;}
			update();
			
			makeDataAviable();
			try {
				Thread.sleep(1);
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
	
	/**
	 * blir kalt når en modul vil kopiere informasjonen til denne modulen
	 * 
	 * @param dataPacker pakkeren som skal ta inn dataen
	 */
	public abstract void copyData(DataPacket<T> dataPacker);
}
