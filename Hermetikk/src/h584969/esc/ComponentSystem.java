package h584969.esc;

import java.util.ArrayList;

import h584969.esc.events.Tickable;

public class ComponentSystem{
	private ArrayList<SystemClass<? extends ComponentClass>> systems;
	private ArrayList<Tickable> tickables;
	
	public ComponentSystem(){
		systems = new ArrayList<SystemClass<? extends ComponentClass>>();
	}
	public void addSystem(SystemClass<? extends ComponentClass> system) {
		systems.add(system);
		system.setID(systems.size()-1);
		
		if (system instanceof Tickable) {
			tickables.add((Tickable)system);
		}
	}
}
