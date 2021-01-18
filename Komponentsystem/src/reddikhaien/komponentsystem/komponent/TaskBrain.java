package reddikhaien.komponentsystem.komponent;

import java.util.ArrayList;
import java.util.List;

import reddikhaien.ai.task.Task;
import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.event.ITickable;

public class TaskBrain extends Component implements ITickable{
	
	private List<Task> tasks;
	private int current;
	public TaskBrain(GameObject object) { 
		super(object); 
		this.tasks = new ArrayList<Task>();
		this.current = tasks.size();
	}
	
	@Override
	public void tick() {
		for(int i = 0; i < current; i++) {
			if(tasks.get(i).shouldExecute()) {
				if(current < tasks.size()-1) {
					tasks.get(current).restart();
					tasks.get(i).startExecute();
					
					current = i;
				}
			}
		}
		if(current < tasks.size()-1) {
			if(tasks.get(current).shouldCountinue()) {
				tasks.get(current).execute();
				return;
			}
			else {
				tasks.get(current).restart();
				current = tasks.size();
			}
		}
	}

}
