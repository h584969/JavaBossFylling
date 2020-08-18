package h584969.esc;

import java.util.ArrayList;

import h584969.esc.events.Tickable;

public class ComponentSystem{
	private ArrayList<SystemClass<? extends ComponentClass>> systems;
	private ArrayList<Tickable> tickables;
	
	public ComponentSystem(){
		
	}
}
