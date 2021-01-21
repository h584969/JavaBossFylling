package reddikhaien.world.chunk;

public class ChunkCoord {
	private final int x;
	private final int y;
	
	public ChunkCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		return (23 * (31 + x)) * (31 + y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ChunkCoord) {
			ChunkCoord o = (ChunkCoord)obj;
			return o.x == this.x && o.y == this.y;
		}
		return false;
	}
	
}
