package h584969.esc;

import java.util.ArrayList;

import h584969.esc.events.Tickable;

public class ComponentSystem{
	private ArrayList<SystemClass<? extends ComponentClass>> systems;
	private ArrayList<Tickable> tickables;
	
	private ArrayList<Thread> threads;
	
	public ComponentSystem(){
		systems = new ArrayList<SystemClass<? extends ComponentClass>>();
		tickables = new ArrayList<>();
		threads = new ArrayList<Thread>();
	}
	public void addSystem(SystemClass<? extends ComponentClass> system) {
		systems.add(system);
		system.setID(systems.size()-1);
		
		if (system instanceof Tickable) {
			tickables.add((Tickable)system);
		}
	}

	public void startEngine() {
		for (int i = 0; i < systems.size(); i++) {
			Thread t = new Thread(systems.get(i));
			threads.add(t);
			t.start();
			
		}
	}
	
	public void terminateEngine() {
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
