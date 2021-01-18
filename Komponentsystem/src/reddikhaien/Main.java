package reddikhaien;

import reddikhaien.komponentsystem.ComponentSystem;
import reddikhaien.komponentsystem.ComponentSystem.Entry;
import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.event.IEvent;
import reddikhaien.komponentsystem.event.ITickable;
import reddikhaien.komponentsystem.komponent.Transform;

public class Main {
	
	
	
	public static void main(String[] args) {
		ComponentSystem system = new ComponentSystem();
		Entry tick = system.addEventType(ITickable.class, (IEvent e) ->{ ((ITickable)e).tick();});
		GameObject object = new GameObject(system);
		object.addComponent(Transform.class);
		
	}
}
