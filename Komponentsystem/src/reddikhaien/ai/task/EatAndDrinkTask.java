package reddikhaien.ai.task;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.Inventory;
import reddikhaien.komponentsystem.komponent.LivingStats;
import reddikhaien.world.item.ItemStack;

public class EatAndDrinkTask extends Task {

	
	private LivingStats stats;
	
	
	
	public EatAndDrinkTask(GameObject object) {
		super();
		this.stats = object.getComponent(LivingStats.class);
	}
	
	@Override
	public boolean shouldExecute() {
		return stats.needsFoodAndDrink();
	}
	
	@Override
	public void startExecute() {
		if (stats.needsFood()) {
			
		}
		
		
	}

}
