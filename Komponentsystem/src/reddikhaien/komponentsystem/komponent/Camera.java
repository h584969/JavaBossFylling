package reddikhaien.komponentsystem.komponent;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.event.IPreDrawable;
import reddikhaien.render.Render;

public class Camera extends Component implements IPreDrawable {

	
	private Transform transform;
	
	public Camera(GameObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		this.transform = getGameObject().getComponent(Transform.class);
	}
	
	@Override
	public void preDraw(Render r) {
		r.setCameraOffset(transform.getPx(), transform.getPy());
	}

}
