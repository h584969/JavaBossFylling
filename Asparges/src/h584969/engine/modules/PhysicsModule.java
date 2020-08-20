package h584969.engine.modules;

import java.util.HashMap;

import h584969.engine.EntityManager;
import h584969.engine.EntityModule;
import h584969.engine.data.TransformData;
import h584969.util.Vector2;

public class PhysicsModule extends EntityModule{
	
	private final HashMap<Long,Vector2> bodies = new HashMap<Long,Vector2>();
	
	public PhysicsModule() {}
	
	
	@Override
	public synchronized void addData(Long id) {
		bodies.put(id,new Vector2());
	}
	
	public synchronized void setVelocity(Long id, Vector2 velocity) {
		bodies.put(id, velocity);
	}
	
	private void performTick() {
		Long[] ids = bodies.keySet().toArray(new Long[bodies.size()]);
		
		TransformData[] data = new TransformData[ids.length];
		
		EntityManager.TRANSFORM.getData(ids, data);
		
		for (int i = 0; i < ids.length; i++) {
			data[i].setPosition(data[i].getPosition().add(bodies.get(ids[i])));
		}

		EntityManager.TRANSFORM.setData(ids, data);
	}
	


	@Override
	public void run() {
		System.out.println("starter: fysikk");
		boolean running = true;
		while(running) {
			int notice = getNotification();
			switch (notice) {
			case 0: continue;
			case TICK: performTick(); break;
			case TERMINATE: running = false; break;
			default:
				throw new IllegalArgumentException("Unexpected value: " +notice);
			}
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
		System.out.println("fysikk terminert");
	}
}
