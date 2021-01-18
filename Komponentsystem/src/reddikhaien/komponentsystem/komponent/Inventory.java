package reddikhaien.komponentsystem.komponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.world.item.IConsumable;
import reddikhaien.world.item.ItemStack;

public class Inventory extends Component{

	private ItemStack[] items;
	
	public Inventory(GameObject object) {
		super(object);
		items = new ItemStack[10];
		setInventorySlots();
	}
	
	public void setInventorySize(int size) {
		items = new ItemStack[size];
		setInventorySlots();
	}
	
	private void setInventorySlots() {
		Arrays.fill(items, ItemStack.EMPTY_STACK);
	}
	
	public boolean consumeItem(int index) {
		if (index < 0 || index > items.length) return false;
		
		
		
		if (items[index].getItem() instanceof IConsumable) {
			boolean used = ((IConsumable)items[index].getItem()).consume(getGameObject());
			if (used) {
				items[index].remove(1);
				if (items[index].getCount() <= 0) {
					items[index] = ItemStack.EMPTY_STACK;
				}
			}
			return used;
		}
		
		return false;
		
	}
	
	public ItemStack findFirstItemWithTag(int tag) {
		for (ItemStack stack : items) {
			
			//sjekker om den gitte tagmasken eksisterer i stack
			// f. eks en item med taggen FOOD, PLANTABLE, COMPOSTABLE vil matche taggen FOOD
			if ((stack.getItem().getTag() & tag) == tag) {
				return stack;
			}
		}
		return null;
	}
	
	public ItemStack[] findItems(Predicate<ItemStack> predicate) {
		ArrayList<ItemStack> matches = new ArrayList<>();
		for (ItemStack stack : items) {
			if (predicate.test(stack)) matches.add(stack);
		}
		
		return matches.toArray(new ItemStack[matches.size()]);
	}
}
