package reddikhaien.world.entity;


import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.Inventory;
import reddikhaien.komponentsystem.komponent.LivingStats;
import reddikhaien.komponentsystem.komponent.Transform;

public class TestEntityRecipe extends EntityRecipe {

	public TestEntityRecipe() {}

	@Override
	public void BuildEntity(GameObject object) {
		object.addComponent(Transform.class);
		object.addComponent(LivingStats.class);
		object.addComponent(Inventory.class).setInventorySize(11);
	}

}
