package reddikhaien.world.item.drink;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.LivingStats;
import reddikhaien.world.item.IConsumable;
import reddikhaien.world.item.Item;
import reddikhaien.world.item.ItemTags;

public class ItemDrink extends Item implements IConsumable{

	private final float amount;
	public ItemDrink(String name, int tag, float amount) {
		super(name, tag | ItemTags.DRINK);
		this.amount = amount;
	}
	public ItemDrink(String name, int tag) {
		this(name,tag,10);
	}
	@Override
	public boolean consume(GameObject object) {
		LivingStats stats = object.getComponent(LivingStats.class);
		if (stats != null) {
			stats.consumeDrink(amount);
			return true;
		}
		return false;
	}
}
