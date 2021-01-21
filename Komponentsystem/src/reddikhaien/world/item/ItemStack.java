package reddikhaien.world.item;

public class ItemStack {

	
	private Item item;
	private int count;
	public ItemStack(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public Item getItem() {
		return item;
	}
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	
	public void remove(int count) {
		this.count -= count;
	}
	public void increase(int count) {
		this.count += count;
	}
	

	public static final ItemStack EMPTY_STACK = new ItemStack(Item.EMPTY, 0);
	
}
