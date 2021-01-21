package reddikhaien.komponentsystem.komponent;

import java.awt.event.KeyEvent;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.event.ITickable;
import reddikhaien.render.Input;

public class PlayerController extends Component implements ITickable{

	private static final float MOVEMENT_SPEED = 0.01f;
	
	
	private Transform transform;
	public PlayerController(GameObject object) {
		super(object);
	}

	@Override
	public void init() {
		this.transform = getGameObject().getComponent(Transform.class);
	}
	
	@Override
	public void tick() {
		if (Input.isHeld(KeyEvent.VK_W)) {
			transform.translate(0, -MOVEMENT_SPEED);
		}
		if (Input.isHeld(KeyEvent.VK_A)){
			transform.translate(-MOVEMENT_SPEED, 0);
		}
		if (Input.isHeld(KeyEvent.VK_S)) {
			transform.translate(0, MOVEMENT_SPEED);
		}
		if (Input.isHeld(KeyEvent.VK_D)){
			transform.translate(MOVEMENT_SPEED, 0);
		}
	}

}
