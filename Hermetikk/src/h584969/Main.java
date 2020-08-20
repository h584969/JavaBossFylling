package h584969;

import h584969.esc.ComponentSystem;

public class Main {

	public static void main(String[] args) {
		ComponentSystem componentSystem = new ComponentSystem();
		TestSystem test1 = new TestSystem();
		TestSystem test2 = new TestSystem();
		componentSystem.addSystem(test1);
		componentSystem.addSystem(test2);
		
		
		componentSystem.startEngine();
		componentSystem.tick();

		
		componentSystem.terminateEngine();
	}

}
