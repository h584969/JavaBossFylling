package h584969.engine.modules.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.xml.crypto.dsig.keyinfo.KeyInfo;

import h584969.Input;
import h584969.engine.EntityManager;
import h584969.engine.EntityModule;
import h584969.engine.IEntityMessage;
import h584969.engine.data.TransformData;
import h584969.engine.data.packet.DataPacket;
import h584969.engine.modules.physics.PhysicsModule;
import h584969.engine.modules.physics.SetPositionMessage;
import h584969.engine.modules.physics.TranslateMessage;
import h584969.graphics.Drawing;

public class PlayerController extends EntityModule<TransformData> {
	final Object lock = new Object();
	
	long id = 0L;
	
	TransformData playerTransform = null;
	
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
		if (playerTransform != null) {
			if (Input.isHeld(KeyEvent.VK_A)) {
				EntityManager.PHYSICS.sendMessage(new TranslateMessage(id, -1.0f, 0.0f));
			}
			if (Input.isHeld(KeyEvent.VK_D)) {
				EntityManager.PHYSICS.sendMessage(new TranslateMessage(id, 1.0f, 0.0f));
			}
			if (Input.isHeld(KeyEvent.VK_W)) {
				EntityManager.PHYSICS.sendMessage(new TranslateMessage(id, 0.0f, -1.0f));
			}
			if (Input.isHeld(KeyEvent.VK_S)) {
				EntityManager.PHYSICS.sendMessage(new TranslateMessage(id, 0.0f, 1.0f));
			}
		}
	}

	@Override
	protected void makeDataAviable() {}

	@Override
	protected void terminated() {}

	@Override
	public void sendMessage(IEntityMessage message) {}

	@Override
	public void copyData(DataPacket<TransformData> dataPacker) {}

}
