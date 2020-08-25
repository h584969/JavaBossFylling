package h584969.engine.modules.player;

import java.awt.event.KeyEvent;
import java.util.Calendar;

import h584969.Input;
import h584969.engine.EntityManager;
import h584969.engine.EntityModule;
import h584969.engine.IEntityMessage;
import h584969.engine.data.TransformData;
import h584969.engine.data.packet.DataPacket;
import h584969.engine.modules.drawing.SetDrawingListenerMessage;
import h584969.engine.modules.drawing.SpriteDrawer;
import h584969.engine.modules.physics.TranslateMessage;
import h584969.graphics.Drawing;

public class PlayerController extends EntityModule {
	private final static float MOVEMENT_SPEED = 10.0f;
	
	private final Object lock = new Object();
	private long id = 0L;
	private TransformData playerTransform = null;
	
	SpriteDrawer PlayerRight = new SpriteDrawer(Drawing.PLAYER_SPRITE_INDEX);
	SpriteDrawer PlayerLeft = new SpriteDrawer(Drawing.PLAYER_LEFT_SPRITE_INDEX);
	int direction = 0;
	
	float timeCounter = 0.0f;
	int updateCounter = 0;
	
	
	@Override
	public synchronized void createNewData(long id) {

		this.id = id;
	}

	@Override
	protected synchronized void retrieveData() {
		long[] ids = new long[] {id};
		DataPacket<TransformData> dataPacker = new DataPacket<>();
		dataPacker.setIds(ids);
		EntityManager.PHYSICS.copyData(dataPacker);
		playerTransform = dataPacker.getData().get(0);
	}

	@Override
	protected void update() {
		float delta = getFixedDeltaTime();
		timeCounter+= delta;
		updateCounter++;
		if (timeCounter >= 1.0f) {
			System.out.println(updateCounter);
			timeCounter = 0;
			updateCounter = 0;
		}
		
		if (playerTransform != null) {
			if (Input.isHeld(KeyEvent.VK_A)) {
				EntityManager.PHYSICS.sendMessage(new TranslateMessage(id, -MOVEMENT_SPEED*delta, 0.0f));
				if (direction != 1) {
					EntityManager.DRAWING.sendMessage(new SetDrawingListenerMessage(id, PlayerLeft));
					direction = 1;
				}
			}
			if (Input.isHeld(KeyEvent.VK_D)) {
				EntityManager.PHYSICS.sendMessage(new TranslateMessage(id, MOVEMENT_SPEED*delta, 0.0f));
				if (direction != 0) {
					EntityManager.DRAWING.sendMessage(new SetDrawingListenerMessage(id, PlayerRight));
					direction = 0;
				}
			}
			if (Input.isHeld(KeyEvent.VK_W)) {
				EntityManager.PHYSICS.sendMessage(new TranslateMessage(id, 0.0f, -MOVEMENT_SPEED*delta));
			}
			if (Input.isHeld(KeyEvent.VK_S)) {
				EntityManager.PHYSICS.sendMessage(new TranslateMessage(id, 0.0f,MOVEMENT_SPEED*delta));
			}
		}
	}

	@Override
	protected void makeDataAviable() {}

	@Override
	protected void terminated() {}

	@Override
	public void sendMessage(IEntityMessage message) {}

}
