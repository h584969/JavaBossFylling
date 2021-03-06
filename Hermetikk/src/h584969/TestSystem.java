package h584969;

import h584969.esc.GameObject;
import h584969.esc.SystemClass;
import h584969.esc.events.Tickable;

public class TestSystem extends SystemClass<TestComponent> implements Tickable {

	@Override
	public void start() {
		System.out.println("vi har startet");
	}

	@Override
	public TestComponent addComponent(GameObject target) {
		return new TestComponent(target);
	}

	@Override
	public void tick() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("tikk nr: " + i + " fra system " + getID());
		}
	}

}
