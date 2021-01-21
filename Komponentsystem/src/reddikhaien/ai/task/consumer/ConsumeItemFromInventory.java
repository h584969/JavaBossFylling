package reddikhaien.ai.task.consumer;

import reddikhaien.ai.task.Task;
import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.Inventory;

public class ConsumeItemFromInventory extends Task {

	
	private GameObject object;
	private Inventory inventory;
	private int itemIndex;
	private int tag;
	public ConsumeItemFromInventory(GameObject object, int tag) {
		super();
		this.object = object;
		this.inventory = object.getComponent(Inventory.class);
		if (inventory == null) throw new IllegalArgumentException("Spillobject må ha en InventoryKomponent!!");
		this.itemIndex = -1;
		this.tag = tag;
	}

	@Override
	public boolean shouldExecute() {
		return inventory.findFirstItemWithTag(tag) != null;
	}
	
	@Override
	public void startExecute() {
		itemIndex = inventory.findItemIndexWithTag(tag);
	}
	
	@Override
	public boolean shouldCountinue() {
		return itemIndex > -1;
	}
	
	@Override
	public void execute() {
		inventory.consumeItem(itemIndex);
		itemIndex = -1;
	}
	
	@Override
	public void restart() {
		itemIndex = -1;
	}
}
