package reddikhaien.world.entity;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.Camera;
import reddikhaien.komponentsystem.komponent.PlayerController;
import reddikhaien.komponentsystem.komponent.Transform;

public class PlayerEntityRecipe extends EntityRecipe {

	@Override
	public void BuildEntity(GameObject object) {
		
		
		object.addComponent(Transform.class);
		object.addComponent(PlayerController.class);
		object.addComponent(Camera.class);
	
		object.init();
	}

}
