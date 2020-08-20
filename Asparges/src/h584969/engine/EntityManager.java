package h584969.engine;


import h584969.engine.modules.PhysicsModule;
import h584969.engine.modules.RenderModule;
import h584969.engine.modules.TransformModule;

public class EntityManager {
	public static final TransformModule TRANSFORM = new TransformModule();
	public static final RenderModule RENDER = new RenderModule();
	public static final PhysicsModule PHYSICS = new PhysicsModule();
	
	private static final Thread[] threads = new Thread[3];
	private static final EntityModule[] modules = new EntityModule[3];
	
	private static long idCounter = 1L;
	
	public static final void start() {
		threads[0] = new Thread(TRANSFORM); modules[0] = TRANSFORM;
		threads[1] = new Thread(RENDER); modules[1] = RENDER;
		threads[2] = new Thread(PHYSICS); modules[2] = PHYSICS;
		
		for (Thread t : threads) {
			t.start();
		}
	}
	
	public static void tick() {
		for (EntityModule e : modules) {
			e.sendNotification(EntityModule.TICK);
		}
	}
	
	public static void terminate() {
		for (int i = 0; i < modules.length; i++) {
			modules[i].sendNotification(EntityModule.TERMINATE);
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static final Long generateID() {
		return idCounter++;
	}
	
	public static final void CreateEntity(EntityModule...entityModules) {
		Long id = generateID();
		for (EntityModule module : entityModules) {
			module.addData(id);
		}
		System.out.println("lagde: " + id);
	}
	
}
