package h584969.engine.modules;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

import h584969.engine.EntityManager;
import h584969.engine.EntityModule;
import h584969.engine.data.RenderData;
import h584969.engine.data.TransformData;
import h584969.util.Vector2;

public class RenderModule extends EntityModule{
	HashMap<Long, RenderData> sprites = new HashMap<Long,RenderData>();
	
	public synchronized void drawSprites(Graphics2D g) {
		Long[] ids = sprites.keySet().toArray(new Long[sprites.size()]);
		
		TransformData[] data = new TransformData[ids.length];
		EntityManager.TRANSFORM.getData(ids, data);
		
		
		
		for (int i = 0; i < ids.length; i++) {
			TransformData transform = data[i];
			RenderData sprite = sprites.get(ids[i]);
			AffineTransform old = g.getTransform();
			g.translate(transform.getPosition().getX() + sprite.getOffset().getX(), transform.getPosition().getY() + sprite.getOffset().getY());
			g.rotate(transform.getRotation());
			g.scale(transform.getScale().getX(), transform.getScale().getY());
			g.setColor(sprite.getColor());
			g.fillRect(0, 0, (int)sprite.getSize().getX(), (int)sprite.getSize().getY());
			g.setTransform(old);
		}
	}
	
	@Override
	public synchronized void addData(Long id) {
		this.addSprite(id, Color.WHITE, new Vector2(10.0f,10.0f), new Vector2());
	}
	
	public synchronized void addSprite(Long id, Color color, Vector2 size, Vector2 offset) {
		sprites.put(id, new RenderData().setColor(color).setSize(size).setOffset(offset));
	}

	@Override
	public void run() {}

	
}
