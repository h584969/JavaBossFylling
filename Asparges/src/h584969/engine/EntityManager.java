package h584969.engine;

import java.util.ArrayList;

import h584969.engine.data.packet.IPacket;
import h584969.engine.modules.drawing.DrawingModule;
import h584969.engine.modules.physics.PhysicsModule;

/*
 * struktur:
 *		dataRetrievalRate: int
 *		getData()
 *		
 *		copyData(buffer,ids)
 *		run() -> fra Runnable
 *		
 * faser:
 * 		1. om vi har ventet lenge nok s� henter vi inn ny data
 * 		2. n�r dataen er hentet kj�res tick funksjonen
 * 		3. n�r vi er ferdig blir dataen lagret for � v�re tilgjengelig for andre moduler
 * 		
 * 
 * 
 */


public class EntityManager {
	private static ArrayList<Thread> threads = new ArrayList<>();
	private static ArrayList<EntityModule<? extends IPacket<? extends Object>>> modules = new ArrayList<>();
	
	public static final PhysicsModule PHYSICS = (PhysicsModule) addModule(new PhysicsModule());
	public static final DrawingModule DRAWING = (DrawingModule) addModule(new DrawingModule());
	
	public static final void start() {
		for (Thread t : threads) {
			t.start();
		}
	}
	public static final void terminate() {
		for (EntityModule<? extends IPacket<? extends Object>> module : modules) {
			module.termiate();
		}
		
		
		for (Thread t : threads) {
			
			try {
			
				t.join();
			
			} catch (InterruptedException e) {
				
				
				e.printStackTrace();
			}
		}
		
		System.out.println("ferdig med � terminere");
	}
	
	
	//TODO finne en litt bedre m�te � gj�re dette her p�
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static EntityModule addModule(EntityModule moduleIn){
		modules.add(moduleIn);
		threads.add(new Thread(moduleIn));
		return moduleIn;
	}
	
	
}
