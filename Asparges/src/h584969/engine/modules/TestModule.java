package h584969.engine.modules;

import java.util.HashSet;

import h584969.engine.EntityManager;
import h584969.engine.EntityModule;
import h584969.engine.data.TransformData;
import h584969.graphics.Drawing;

public class TestModule extends EntityModule {
	private HashSet<Long> entities = new HashSet<>();
	
	
	@Override
	public synchronized void addData(Long id) {
		entities.add(id);
	}
	
	private void tick() {
		Long[] ids = entities.toArray(new Long[entities.size()]);
		TransformData[] data = new TransformData[ids.length];
		
		EntityManager.TRANSFORM.getData(ids, data);
		for (int i = 0; i < ids.length; i++) {
			float x = data[i].getPosition().getX();
			float y = data[i].getPosition().getY();
			
			if (y < 0.0f)  y = Drawing.WIDTH*Drawing.SCALE;
			if (y > Drawing.WIDTH*Drawing.SCALE) y = 0.0f;
			
		}
	}

	@Override
	public void run() {
		boolean running = true;
		while(running) {
			int n = getNotification();
			switch (n) {
				case TERMINATE: running = false; break; 
				case TICK: tick(); break;
			}
		}
	}
	

}
