package reddikhaien.world.enviroment;

import reddikhaien.render.Render;

public class SimpleTile extends AbstractTile {

	
	int spriteID;
	public SimpleTile(String name, int spriteID) {
		super(name);
		this.spriteID = spriteID;
		
	}
	@Override
	public void draw(Render g, int x, int y) {
		g.drawSpriteInWorld(spriteID, x, y);
	}

}
