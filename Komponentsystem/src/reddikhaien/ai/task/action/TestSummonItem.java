package reddikhaien.ai.task.action;

import reddikhaien.ai.task.Task;
import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.Inventory;
import reddikhaien.world.item.Item;

public class TestSummonItem  extends Task{

	private Inventory inventory;
	private Item item;
	public TestSummonItem(GameObject object, Item item) {
		inventory = object.getComponent(Inventory.class);
		this.item = item;
	}
	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void startExecute() {
		System.out.println("la til " + item.getName());
		inventory.addItem(item);
	}
}
