package h584969.engine.modules.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;

import h584969.engine.EntityManager;
import h584969.engine.EntityModule;
import h584969.engine.IEntityMessage;
import h584969.engine.data.TransformData;
import h584969.engine.data.packet.DataPacket;

public class DrawingModule extends EntityModule{

	
	private final HashSet<Long> sprites = new HashSet<>();
	private long[] idList = null;
	private TransformData[] dataList = null;
	
	private final Object lock = new Object();
	
	@Override
	public void createNewData(long id) {
		synchronized (sprites) {
			sprites.add(id);
		}
	}

	@Override
	protected void retrieveData() {
		synchronized (sprites) {
			synchronized (lock) {
				idList = new long[sprites.size()];
				int index = 0;
				for (Long k : sprites) {
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
	public synchronized void sendMessage(IEntityMessage message) {}
	
	public void drawSprites(final Graphics2D g2d) {
		synchronized (g2d) {
			synchronized (lock) {
				g2d.setColor(Color.WHITE);
				if (dataList == null) return;
				for (int i = 0; i < dataList.length; i++) {
					g2d.fillRect((int)dataList[i].getPosition().getX(), (int)dataList[i].getPosition().getY(), 100, 100);
				}
			}
		}
	}
	
}
