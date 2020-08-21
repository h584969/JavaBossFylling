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
	public static final int TRANSLATE = 4;
	public static final int SCALE = 5;
	public static final int ROTATE = 6;
	public static final int TRANSFORM = 7;
	
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

	private void setPosition(TransformData data, float x, float y) {
		data.getPosition().setX(x);
		data.getPosition().setY(y);
		data.markDirty();
	}
	private void translate(TransformData data, float x, float y) {
		Vector2 p = data.getPosition();
		p.setX(p.getX()+x);
		p.setY(p.getY()+y);
		data.markDirty();
	}
	private void setScale(TransformData data, float x, float y) {
		data.getScale().setX(x);
		data.getScale().setY(y);
		data.markDirty();
	}
	private void scale(TransformData data, float x, float y) {
		Vector2 s = data.getScale();
		s.setX(s.getX()*x);
		s.setX(s.getX()*y);
		
		data.markDirty();
	}
	private void setRotation(TransformData data, float a) {
		data.setRotation(a);
		data.markDirty();
	}
	private void rotate(TransformData data, float a) {
		data.setRotation(data.getRotation()+a);
		data.markDirty();
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
						setPosition(t, msg.x, msg.y);
					}
				}catch (ClassCastException e) {
					logInvalidMessageType(SetPositionMessage.class.getName(), message.getClass().getName());
					e.printStackTrace();
				}
			}break;
			
			
			case SET_SCALE: {
				try {
					SetScaleMessage msg = (SetScaleMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						setScale(t, msg.x, msg.y);
						
					}
				}catch (ClassCastException e) {
					logInvalidMessageType(SetScaleMessage.class.getName(), message.getClass().getName());
					e.printStackTrace();
				}
				
			}break;
			
			
			case SET_ROTATION: {
				try {
					SetRotationMessage msg = (SetRotationMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						setRotation(t, msg.rotation);
					}
				}catch (ClassCastException e) {
					logInvalidMessageType(SetRotationMessage.class.getName(), message.getClass().getName());
					e.printStackTrace();
				}
				
			}break;
			
			
			case TRANSLATE:{
				try {
					TranslateMessage msg = (TranslateMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						translate(t, msg.x, msg.y);
					}
				}catch (ClassCastException e) {
					logInvalidMessageType(TranslateMessage.class.getName(), message.getClass().getName());
					e.printStackTrace();
				}
			}break;
			
			
			case SCALE:{
				try {
					ScaleMessage msg = (ScaleMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						scale(t, msg.x, msg.y);
					}
				}catch (ClassCastException e) {
					logInvalidMessageType(ScaleMessage.class.getName(), message.getClass().getName());
					e.printStackTrace();
				}
			}break;
			
			case ROTATE:{
				try {
					RotateMessage msg = (RotateMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						rotate(t, msg.rotation);
					}
				}catch (ClassCastException e) {
					logInvalidMessageType(ScaleMessage.class.getName(), message.getClass().getName());
					e.printStackTrace();
				}
			} break;
			
			case TRANSFORM: {
				try {
					TransformMessage msg = (TransformMessage)message;
					synchronized (processingData) {
						TransformData t = processingData.get(msg.id);
						translate(t, msg.posx, msg.posy);
						scale(t, msg.scalex, msg.scaley);
						rotate(t, msg.rotation);
					}
				}catch (ClassCastException e) {
					logInvalidMessageType(ScaleMessage.class.getName(), message.getClass().getName());
					e.printStackTrace();
				}
			} break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + message.getId());
		}
	}



}
