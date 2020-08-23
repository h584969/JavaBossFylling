package h584969.engine.modules.drawing;

import java.awt.Graphics2D;
import java.util.HashMap;

import h584969.engine.EntityManager;
import h584969.engine.EntityModule;
import h584969.engine.IEntityMessage;
import h584969.engine.data.TransformData;
import h584969.engine.data.packet.DataPacket;
import h584969.graphics.Drawing;


class SpriteData{
	IDrawingListener listener;
}

public class DrawingModule extends EntityModule{

	public static final int SET_DRAWING_LISTENER = 1;
	
	private final HashMap<Long,IDrawingListener> sprites = new HashMap<Long, IDrawingListener>();
	private long[] idList = null;
	private TransformData[] dataList = null;
	
	private final Object lock = new Object();
	
	@Override
	public void createNewData(long id) {
		synchronized (sprites) {
			sprites.put(id,new SpriteDrawer(Drawing.PLAYER_SPRITE_INDEX));
		}
	}

	@Override
	protected void retrieveData() {
		synchronized (sprites) {
			synchronized (lock) {
				idList = new long[sprites.size()];
				int index = 0;
				for (Long k : sprites.keySet()) {
					idList[index++] = k;
				}
				
				DataPacket<TransformData> dataPacker = new DataPacket<>();
				dataPacker.setIds(idList);
				EntityManager.PHYSICS.copyData(dataPacker);
				
				dataList = dataPacker.getData().toArray(new TransformData[dataPacker.getData().size()]);
			}
		}
	}

	@Override
	protected void update() {}

	@Override
	protected void makeDataAviable() {}

	@Override
	protected void terminated() {}

	@Override
	public synchronized void sendMessage(IEntityMessage message) {
		switch(message.getId()) {
		case SET_DRAWING_LISTENER:{
			SetDrawingListenerMessage msg = (SetDrawingListenerMessage)message;
			synchronized (sprites) {
				sprites.put(msg.id, msg.listener);
			}
		}break;
		default: throw new IllegalArgumentException("uforventet meldingsID: " + message.getId());
		}
	}
	
	public void drawSprites(final Graphics2D g2d, final Drawing drawing) {
		synchronized (g2d) {
			synchronized (lock) {
				if (dataList == null) return;
				for (int i = 0; i < dataList.length; i++) {
					
					g2d.translate(dataList[i].getPosition().getX(), dataList[i].getPosition().getY());
					g2d.rotate(dataList[i].getRotation());
					g2d.scale(dataList[i].getScale().getX(), dataList[i].getScale().getY());
					
					sprites.get(idList[i]).draw(g2d, drawing);
				}
			}
		}
	}
	
}
