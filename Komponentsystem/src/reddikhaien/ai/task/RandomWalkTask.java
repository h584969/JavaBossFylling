package reddikhaien.ai.task;

import java.util.Random;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.Transform;

public class RandomWalkTask extends Task {
	
	private static Random random = new Random();
	
	
	private Transform transform;
	private float range;
	private float movementSpeed;
	
	private float dx, dy;
	private float mx, my;
	private boolean running;
	public RandomWalkTask(GameObject object, float range, float movementSpeed) {
		super();
		this.transform = object.getComponent(Transform.class);
		this.range = range;
		this.movementSpeed = movementSpeed;
		this.running = false;
	}
	
	@Override
	public boolean shouldExecute() {
		return random.nextInt(100) < 80;
	}
	
	@Override
	public void startExecute() {
		pickRandomLocation();
	}
	
	@Override
	public boolean shouldCountinue() {
		return running && transform.sqrDistanceTo(dx, dy) > 1f;
	}
	
	@Override
	public void execute() {
		transform.translate(mx, my);
	}
	
	private void pickRandomLocation() {
		dx = transform.getPx() + (random.nextFloat() - 0.5f)*range;
		dy = transform.getPy() + (random.nextFloat() - 0.5f)*range;
		float distance = (float)Math.sqrt(transform.sqrDistanceTo(dx, dy));
		mx = (dx - transform.getPx())/distance;
		my = (dy - transform.getPy())/distance;
		
		mx *= movementSpeed;
		my *= movementSpeed;
		running = true;
	}
	
	@Override
	public void restart() {
		running = false;
	}
}
