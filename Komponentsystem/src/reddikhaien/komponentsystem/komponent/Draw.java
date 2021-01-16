package reddikhaien.komponentsystem.komponent;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.event.IDrawable;

public class Draw extends Component implements IDrawable{

	public Draw(GameObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		Transform t = getGameObject().getComponent(Transform.class);
	}
	
	

}
