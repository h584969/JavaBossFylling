package reddikhaien.ai.task;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.LivingStats;

public class EatAndDrinkTask extends Task {

	
	private LivingStats stats;
	
	private Task foodFetchTasks[];
	private Task drinkFetchTasks[];
	
	private Task currentTask;
	
	public EatAndDrinkTask(GameObject object, Task[] foodFetchTasks, Task[] drinkFetchTasks) {
		super();
		this.stats = object.getComponent(LivingStats.class);
		this.foodFetchTasks = foodFetchTasks;
		this.drinkFetchTasks = drinkFetchTasks;
		this.currentTask = null;
	}
	
	@Override
	public boolean shouldExecute() {
		//System.out.println("tester!!!");
		return stats.needsFoodAndDrink();
	}
	
	@Override
	public void startExecute() {
		System.out.println("starter å skaffe næring");
		if (stats.needsDrink()) {
			currentTask = getFirstTrue(drinkFetchTasks);	
		}
		
		if (currentTask == null && stats.needsFood()) {
			currentTask = getFirstTrue(foodFetchTasks);
		}
		
		
		if (currentTask != null) {
			currentTask.startExecute();
		}
	}
	
	@Override
	public boolean shouldCountinue() {
		return currentTask != null;
	}
	
	@Override
	public void execute() {
		System.out.println("gjennomfører spising og drikking");
		if (currentTask == null) return;
		
		if (currentTask.shouldCountinue()) {
			currentTask.execute();
		}
		else {
			currentTask.restart();
			currentTask = null;
		}
		
	}
	
	private Task getFirstTrue(Task[] taskList) {
		for (Task task : taskList) {
			if (task.shouldExecute()) return task;
		}
		return null;
	}

	@Override
	public void restart() {
		System.out.println("restarter spising");
		if (currentTask != null) currentTask.restart();
		currentTask = null;
	
	}
	
}
