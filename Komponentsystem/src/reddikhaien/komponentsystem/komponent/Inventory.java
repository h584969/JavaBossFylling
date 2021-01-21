package reddikhaien.komponentsystem.komponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.world.item.IConsumable;
import reddikhaien.world.item.Item;
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
	
	public void addItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			
			if (items[i].getItem().equals(item)) {
				System.out.println("økte " + i + " med 1");
				items[i].increase(1);
				System.out.println(items[i].getItem().getName());
				return;
			}
			else if(items[i] == ItemStack.EMPTY_STACK) {
				System.out.println("lagde ny på " + i);
				items[i] = new ItemStack(item, 1);
				System.out.println(items[i].getItem().getName() + " " + items[i].getItem().getTag());
				return;
			}
		}
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
	
	public int findItemIndexWithTag(int tag) {
		for (int i = 0; i < items.length; i++) {
			if ((items[i].getItem().getTag() & tag) == tag) return i;
		}
		return -1;
	}
	
	public ItemStack findFirstItemWithTag(int tag) {
		for (ItemStack stack : items) {
			System.out.println(stack.getItem().getTag() + " ==== " + tag);
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
