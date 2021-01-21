package reddikhaien.world;

import java.util.HashMap;
import java.util.Map;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.komponent.Transform;
import reddikhaien.render.Render;
import reddikhaien.world.chunk.ChunkCoord;

public class World {
	private GameObject player;
	private Transform playerTransform;
	private Map<ChunkCoord, Chunk> chunks;
	
	public World(GameObject player) {
		this.player = player;
		this.playerTransform = player.getComponent(Transform.class);
		this.chunks = new HashMap<>();
	}
	
	public void tick() {
		int cx = ((int)playerTransform.getPx())/Chunk.SIZE;
		int cy = ((int)playerTransform.getPy())/Chunk.SIZE;
		
		ChunkCoord coords = new ChunkCoord(cx, cy);
		
		if (!chunks.containsKey(coords)) {
			chunks.put(coords, new Chunk(cx,cy));
			chunks.get(coords).generate();
		}
	}
	public void draw(Render r) {
		int cx = ((int)playerTransform.getPx())/Chunk.SIZE;
		int cy = ((int)playerTransform.getPy())/Chunk.SIZE;
		
		ChunkCoord coords = new ChunkCoord(cx, cy);
		
		if (chunks.containsKey(coords)) {
			chunks.get(coords).drawChunk(r);
		}

	}
}
