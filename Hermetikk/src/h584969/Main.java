package h584969;

import h584969.esc.ComponentSystem;

public class Main {

	public static void main(String[] args) {
		ComponentSystem componentSystem = new ComponentSystem();
		TestSystem test = new TestSystem();
		componentSystem.addSystem(test);
		componentSystem.startEngine();
	}

}
