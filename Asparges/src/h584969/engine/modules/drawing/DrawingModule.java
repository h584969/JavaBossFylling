package h584969.engine.modules.drawing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import h584969.engine.EntityManager;
import h584969.engine.EntityModule;
import h584969.engine.IEntityMessage;
import h584969.engine.data.TransformData;
import h584969.engine.data.packet.DataPacket;
import h584969.graphics.Drawing;


//hjelpeklasse for datainterpolering
class DataTimeStamp{

	public DataTimeStamp(long timestamp, TransformData data) {
		oldTimestamp = timestamp;
		curTimestamp = timestamp;
		old = data;
		cur = data;
	}
	
	long oldTimestamp = 0L;
	long curTimestamp = 0L;

	TransformData cur;
	TransformData old;
	
	void update(long timestamp, TransformData data) {
		oldTimestamp = curTimestamp;
		curTimestamp = timestamp;
		old = cur;
		cur = data;
	}
	
	public float interpolate(long t, float va, float vb) {
		return va + (t - oldTimestamp)*((vb - va)/(curTimestamp - oldTimestamp));
	}
	
	public float positionX(long t) {
		return interpolate(t, old.getPosition().getX(), cur.getPosition().getX());
	}
	public float positionY(long t) {
		return interpolate(t, old.getPosition().getY(), cur.getPosition().getY());
	}
	public float scaleX(long t) {
		return interpolate(t, old.getScale().getX(), cur.getScale().getX());
	}
	public float scaleY(long t) {
		return interpolate(t, old.getScale().getY(), cur.getScale().getY());
	}
	public float rotation(long t) {
		return interpolate(t, old.getRotation(), cur.getRotation());
	}
}

public class DrawingModule extends EntityModule{

	public static final int SET_DRAWING_LISTENER = 1;
	public static final int SET_CAMERA = 2;
	public static final int SET_CAMERA_OFFSET = 3;
	
	private final HashMap<Long,IDrawingListener> sprites = new HashMap<Long, IDrawingListener>();
	
	private final HashMap<Long, DataTimeStamp> dataTimeStamps = new HashMap<Long, DataTimeStamp>();
	
	private final Object cameraIDLock = new Object();
	private long cameraID;
	private float offsetX = 0.0f;
	private float offsetY = 0.0f;
	
	public DrawingModule() {
		dataRetrievalRate = 1;
	}
	
	@Override
	public void createNewData(long id) {
		synchronized (sprites) {
			sprites.put(id,new SpriteDrawer(Drawing.PLAYER_SPRITE_INDEX));
		}
	}

	@Override
	protected void retrieveData() {}

	private synchronized void getDataonDraw() {

		long timestamp = Calendar.getInstance().getTimeInMillis();

		long[] idList = new long[sprites.size()];
		int index = 0;

		for (Long k : sprites.keySet()) {
			idList[index++] = k;
		}

		DataPacket<TransformData> dataPacker = new DataPacket<>();
		dataPacker.setIds(idList);
		EntityManager.PHYSICS.copyData(dataPacker);

		TransformData[] dataList = dataPacker.getData().toArray(new TransformData[dataPacker.getData().size()]);

		for (int i = 0; i < dataList.length; i++) {
			if (!dataTimeStamps.containsKey(idList[i])) {
				dataTimeStamps.put(idList[i], new DataTimeStamp(timestamp, dataList[i]));
			}
			else {
				dataTimeStamps.get(idList[i]).update(timestamp, dataList[i]);
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
		
		
		case SET_CAMERA:{
			SetCameraMessage msg = (SetCameraMessage)message;
			synchronized (cameraIDLock) {
				cameraID = msg.id;
			}
		}break;
		
		case SET_CAMERA_OFFSET:{
			SetCameraOffsetMessage msg = (SetCameraOffsetMessage)message;
			synchronized (cameraIDLock) {
				offsetX = msg.x;
				offsetY = msg.y;
			}
		}break;
		
		
		default: throw new IllegalArgumentException("uforventet meldingsID: " + message.getId());
		}
	}
	
	public synchronized void drawSprites(final Graphics2D g2d, final Drawing drawing, long frameTime) {
		getDataonDraw();
		
		long timestamp = Calendar.getInstance().getTimeInMillis() - frameTime;
		
		AffineTransform world = g2d.getTransform();
		if (cameraID != 0L) {
			g2d.translate(-dataTimeStamps.get(cameraID).positionX(timestamp) + offsetX,-dataTimeStamps.get(cameraID).positionY(timestamp) + offsetY);
		}
		
		for(Map.Entry<Long, DataTimeStamp> data : dataTimeStamps.entrySet()) {
			AffineTransform t = g2d.getTransform();
			g2d.translate(data.getValue().positionX(timestamp),data.getValue().positionY(timestamp));
			g2d.rotate(data.getValue().rotation(timestamp));
			g2d.scale(data.getValue().scaleX(timestamp), data.getValue().scaleY(timestamp));

			sprites.get(data.getKey()).draw(g2d, drawing);
			g2d.setTransform(t);
		}
		g2d.setTransform(world);
	}
	
}
