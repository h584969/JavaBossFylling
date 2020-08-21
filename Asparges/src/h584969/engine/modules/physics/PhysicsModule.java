package h584969.engine.modules.physics;

import java.util.HashMap;
import java.util.Map;

import h584969.engine.EntityModule;
import h584969.engine.IEntityMessage;
import h584969.engine.data.TransformData;
import h584969.engine.data.packet.DataPacket;
import h584969.util.Vector2;

public class PhysicsModule extends EntityModule<TransformData> {
	public static final int SET_POSITION = 1;
	public static final int SET_SCALE = 2;
	public static final int SET_ROTATION = 3;
	private final HashMap<Long, TransformData> availableData = new HashMap<>();
	private final HashMap<Long, TransformData> processingData = new HashMap<>();
	
	
	
	@Override
	public void createNewData(long id) {
		//låser både inn og ut
		synchronized (availableData) {
			synchronized (processingData) {
				processingData.put(id, new TransformData());
				availableData.put(id, new TransformData());
			}
		}
	}
	
	@Override
	protected void retrieveData() {
		//fysikkmotoren skal ikke ta inn noen konkret form for data
		
	}

	@Override
	protected void makeDataAviable() {
		//låser både inn og ut
		synchronized (processingData) {
			synchronized (processingData) {
				//kopierer bare elementer som er endret
				for (Map.Entry<Long, TransformData> entry : processingData.entrySet()) {
					if (entry.getValue().isChanged()) {

						availableData.put(entry.getKey(), entry.getValue().createCopy());

						//nå som dataen er oppdatert kan vi markere den som ren
						entry.getValue().markClean();
					}
				}
			}
		}
	}

	@Override
	protected void update() {
		//låser bare prosessdata, tilgjengelig data er fri til å bli kopiert
		synchronized (processingData) {
			
		}
	}

	@Override
	public void copyData(DataPacket<TransformData> dataPacker) {
		//låser bare tilgjengelig data
		synchronized (availableData) {
			long[] ids = dataPacker.getIds();
			
			if (ids != null) {
				
				for (long id : ids) {
					dataPacker.addData(availableData.get(id));
				}
			}
			else {
				
				long[] newIds = new long[availableData.size()];
				int index = 0;
				for (Map.Entry<Long, TransformData> entry: availableData.entrySet()) {
					
					dataPacker.addData(entry.getValue());
					newIds[index++] = entry.getKey();
				}
				dataPacker.setIds(newIds);
			}
	
		}			
	}

	@Override
	protected void terminated() {
		
		
	}

	@Override
	public synchronized void sendMessage(IEntityMessage message) {
			switch (message.getId()) {
			case SET_POSITION: {
				try {
					SetPositionMessage msg = (SetPositionMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						Vector2 p = t.getPosition();
						p.setX(msg.x);
						p.setY(msg.y);
						t.setPosition(p);
						t.markDirty();
					}
				}catch (ClassCastException e) {
					System.err.println(message.getClass().getName() + " er ikke en gyldig melding for " + SetPositionMessage.class.getName());
					e.printStackTrace();
				}
			}break;
			case SET_SCALE: {
				try {
					SetScaleMessage msg = (SetScaleMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						Vector2 p = t.getScale();
						p.setX(msg.x);
						p.setY(msg.y);
						t.setScale(p);
						t.markDirty();
					}
				}catch (ClassCastException e) {
					System.err.println(message.getClass().getName() + " er ikke en gyldig melding for " + SetScaleMessage.class.getName());
					e.printStackTrace();
				}
				
			}break;
			case SET_ROTATION: {
				try {
					SetRotationMessage msg = (SetRotationMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						t.setRotation(msg.rotation);
						t.markDirty();
					}
				}catch (ClassCastException e) {
					System.err.println(message.getClass().getName() + " er ikke en gyldig melding for " + SetPositionMessage.class.getName());
					e.printStackTrace();
				}
				
			}break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + message.getId());
			}
		
	}



}
