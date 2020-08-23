package h584969.engine;

import java.util.ArrayList;

import h584969.engine.modules.drawing.DrawingModule;
import h584969.engine.modules.physics.PhysicsModule;
import h584969.engine.modules.player.PlayerController;

/*
 * struktur:
 *		dataRetrievalRate: int
 *		getData()
 *		
 *		copyData(buffer,ids)
 *		run() -> fra Runnable
 *		
 * faser:
 * 		1. om vi har ventet lenge nok så henter vi inn ny data
 * 		2. når dataen er hentet kjøres tick funksjonen
 * 		3. når vi er ferdig blir dataen lagret for å være tilgjengelig for andre moduler
 * 		
 * 
 * 
 */


public class EntityManager {
	private static ArrayList<Thread> threads = new ArrayList<>();
	private static ArrayList<EntityModule> modules = new ArrayList<>();
	
	public static final PhysicsModule PHYSICS = (PhysicsModule) addModule(new PhysicsModule());
	public static final DrawingModule DRAWING = (DrawingModule) addModule(new DrawingModule());
	public static final PlayerController PLAYER_CONTROLLER = (PlayerController) addModule(new PlayerController());
	public static final void start() {
		for (Thread t : threads) {
			t.start();
		}
	}
	public static final void terminate() {
		for (EntityModule module : modules) {
			module.termiate();
		}
		
		
		for (Thread t : threads) {
			
			try {
			
				t.join();
			
			} catch (InterruptedException e) {
				
				
				e.printStackTrace();
			}
		}
		
		System.out.println("ferdig med å terminere");
	}
	
	
	private static EntityModule addModule(EntityModule moduleIn){
		modules.add(moduleIn);
		threads.add(new Thread(moduleIn));
		return moduleIn;
	}
	
	
}
