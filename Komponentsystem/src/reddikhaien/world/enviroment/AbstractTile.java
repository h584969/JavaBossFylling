package reddikhaien.world.enviroment;

import reddikhaien.render.Render;

public abstract class AbstractTile {
	
	private final String name;
	public static final AbstractTile AIR = new SimpleTile("air", 0);
	public static final AbstractTile EARTH = new SimpleTile("earth", 1);
	public static final AbstractTile WATER = new SimpleTile("water", 2);
	
	public AbstractTile(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public abstract void draw(Render g, int worldx, int worldy);
}
