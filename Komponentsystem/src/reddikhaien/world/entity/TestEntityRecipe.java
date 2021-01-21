package reddikhaien.world.entity;


import reddikhaien.ai.task.EatAndDrinkTask;
import reddikhaien.ai.task.RandomWalkTask;
import reddikhaien.ai.task.Task;
import reddikhaien.ai.task.action.TestSummonItem;
import reddikhaien.ai.task.consumer.ConsumeItemFromInventory;
import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.Draw;
import reddikhaien.komponentsystem.komponent.Inventory;
import reddikhaien.komponentsystem.komponent.LivingStats;
import reddikhaien.komponentsystem.komponent.TaskBrain;
import reddikhaien.komponentsystem.komponent.Transform;
import reddikhaien.world.item.Item;
import reddikhaien.world.item.ItemTags;

public class TestEntityRecipe extends EntityRecipe {

	public TestEntityRecipe() {}

	@Override
	public void BuildEntity(GameObject object) {
		
		object.addComponent(Transform.class).translate(400, 400);
		object.addComponent(LivingStats.class);
		object.addComponent(Inventory.class).setInventorySize(11);
		object.addComponent(Draw.class);
		
		ConsumeItemFromInventory consumeFoodItem = new ConsumeItemFromInventory(object, ItemTags.FOOD);
		TestSummonItem summonFood = new TestSummonItem(object, Item.PROTEIN_BAR);
		
		ConsumeItemFromInventory consumeDrinkItem = new ConsumeItemFromInventory(object, ItemTags.DRINK);
		TestSummonItem summonDrink = new TestSummonItem(object, Item.WATER_BOTTLE);
		
		EatAndDrinkTask eatDrinkTask = new EatAndDrinkTask(object, new Task[] {consumeFoodItem, summonFood}, new Task[] {consumeDrinkItem, summonDrink});
		RandomWalkTask walkTask = new RandomWalkTask(object, 600.0f, 1.0f);
		
		
		object.addComponent(TaskBrain.class).addTasks(eatDrinkTask,walkTask);
		
		
		object.init();
	}

}
