package reddikhaien.world.item;

public class Item {
	private static int IDCounter = 1;
	
	
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

	
	public static final Item EMPTY = new Item("none");
}
