package reddikhaien.komponentsystem.komponent;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.event.IDrawable;
import reddikhaien.render.Render;

public class Draw extends Component implements IDrawable{

	public Draw(GameObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Render graphics) {
		Transform t = getGameObject().getComponent(Transform.class);
		graphics.drawSpriteInWorld(3, (int)t.getPx(), (int)t.getPy());
	}
	
	

}
