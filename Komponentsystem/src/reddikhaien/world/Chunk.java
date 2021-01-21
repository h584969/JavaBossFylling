package reddikhaien.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.render.Render;
import reddikhaien.world.chunk.TilePalette;
import reddikhaien.world.chunk.TilePalette.PaletteEntry;
import reddikhaien.world.enviroment.AbstractTile;

public class Chunk {
	
	public static final int SIZE = 32;
	
	private static final Random random = new Random();
	
	private List<GameObject> entities;
	private TilePalette palette;
	private short[] tiles;
	
	
	private int cx, cy;
	public Chunk(int cx, int cy) {
		this.entities = new ArrayList<>();
		this.palette = new TilePalette();
		this.tiles = new short[SIZE*SIZE];
		this.cx = cx;
		this.cy = cy;
	}
	
	public void generate() {
		for (int i = 0; i < SIZE*SIZE; i++) {
			int x = i%SIZE;
			int y = i/SIZE;
			
			placeTile(random.nextInt(100) < 50 ? AbstractTile.EARTH : AbstractTile.WATER, x, y);
		}
	}
	
	
	/**
	 * Plasserer flaten tile inn i chunken på posisjonen spesifisert med x og y
	 * @param tile flate å plassere
	 * @param x posisjonen langs X aksen
	 * @param y posisjonen langs Y aksen
	 */
	public void placeTile(AbstractTile tile, int x, int y) {
		x %= SIZE;
		y %= SIZE;
		
		PaletteEntry prev = getPaletteEntry(x, y);
		if (prev.tile != AbstractTile.AIR) {
			removeTile(x, y);
		}
		
		setTile(tile, x, y);
		System.out.println(palette.toString());
	}
	
	/**
	 * Oppdaterer palettet og setter flaten på (x,y) til 0(AIR)
	 * @param x
	 * @param y
	 */
	private void removeTile(int x, int y) {
		short id = tiles[x + y*SIZE];
		palette.reduceTileCount(id);
		tiles[x + y*SIZE] = 0;
	}
	
	private void setTile(AbstractTile tile, int x, int y) {
		short id = palette.getOrAddTileId(tile);
		tiles[x + y*SIZE] = id;
		palette.getEntry(id).tileCount++;
	}
	
	private PaletteEntry getPaletteEntry(int x, int y) {
		return palette.getEntry(tiles[x + y*SIZE]);
	}
	
	
	public void drawChunk(Render r) {
		for (int i = 0; i < SIZE*SIZE; i++) {
			int x = i%SIZE;
			int y = i/SIZE;
			PaletteEntry entry = getPaletteEntry(x, y);
			entry.tile.draw(r, x + cx*SIZE, y + cy*SIZE);
		}
	}
}
