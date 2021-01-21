package reddikhaien.world.item;

import reddikhaien.world.item.drink.ItemDrink;
import reddikhaien.world.item.food.ItemFood;

public class Item {
	private static int IDCounter = 1;
	
	public static final Item EMPTY = new Item("none");
	
	public static final Item PROTEIN_BAR = new ItemFood("protein bar", 0);
	
	public static final Item WATER_BOTTLE = new ItemDrink("water bottle", 0);
	
	private final int id;
	private final String name;
	private final int tag;
	
	public Item(String name, int tag) {
		id = IDCounter++;
		this.name = name;
		this.tag = tag;
	}
	
	public Item(String name) {
		this(name,ItemTags.DEFAULT);
	}
	
	public int getId() {
		return id;
	}
	
	public int getTag() {
		return tag;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (obj instanceof Item) {
			return ((Item)obj).id == this.id;
		}
		return false;
	}

	
	
}
