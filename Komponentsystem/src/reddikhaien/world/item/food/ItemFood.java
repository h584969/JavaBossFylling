package reddikhaien.world.item.food;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.LivingStats;
import reddikhaien.world.item.IConsumable;
import reddikhaien.world.item.Item;
import reddikhaien.world.item.ItemTags;

public class ItemFood extends Item implements IConsumable {

	
	private final float amount;
	public ItemFood(String name,int amount, int tag) {
		super(name,tag | ItemTags.FOOD);
		this.amount = amount;
	}
	public ItemFood(String name, int amount) {
		this(name,amount,ItemTags.DEFAULT);
	}

	@Override
	public boolean consume(GameObject object) {
		LivingStats stats = object.getComponent(LivingStats.class);
		if (stats != null) {
			stats.consumeFood(amount);
		}
		return false;
	}

}
