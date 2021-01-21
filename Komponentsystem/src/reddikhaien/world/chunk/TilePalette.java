package reddikhaien.world.chunk;

import java.util.ArrayList;

import reddikhaien.world.enviroment.AbstractTile;

public class TilePalette {
	private ArrayList<PaletteEntry> palette;
	
	public TilePalette() {
		this.palette = new ArrayList<>();
		palette.add(new PaletteEntry());
	}
	
	public short getOrAddTileId(AbstractTile tile) {
		short emptyPos = -1;
		for (short i = 0; i < palette.size(); i++) {
			if (palette.get(i) == null && emptyPos == -1) emptyPos = i;
			else if (palette.get(i).tile == tile) return i;
		}
		
		if (emptyPos > -1) {
			palette.set(emptyPos, new PaletteEntry(tile));
			return emptyPos;
		}
		else {
			palette.add(new PaletteEntry(tile));
			return (short)(palette.size()-1);
		}
	}
	
	public PaletteEntry getEntry(short id) {
		return palette.get(id);
	}
	
	public void reduceTileCount(short id) {
		PaletteEntry entry = palette.get(id);
		entry.tileCount--;
		if (entry.tileCount <= 0) {
			palette.set(id, null);
		}
	}
	
	
	@Override
	public String toString() {
		String s = "[";
		for (int i = 0; i < palette.size(); i++) {
			s += i+": " + palette.get(i).toString();
		}
		return s + "]";
	}
	
	public class PaletteEntry{
		public int tileCount;
		public AbstractTile tile;
		public PaletteEntry(AbstractTile tile, int tileCount) {
			this.tileCount = tileCount;
			this.tile = tile;
		}
		public PaletteEntry(AbstractTile tile) {
			this(tile,0);
		}
		public PaletteEntry() {
			this(AbstractTile.AIR,0);
		}
		
		@Override
		public String toString() {
			return tile.toString()+"x"+tileCount;
		}
	}
}
