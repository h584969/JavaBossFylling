package h584969.engine;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public abstract class EntityModule implements Runnable{
	public static final int TERMINATE = 1;
	public static final int TICK = 2;
	
	private Queue<Integer> notifications = new LinkedTransferQueue<Integer>();

	
	
	public abstract void addData(Long id);
	
	public final synchronized void sendNotification(int notification) {
		notifications.add(notification);
	}
	
	public final int getNotification() {
		return notifications.peek() != null ? notifications.remove() : 0;
	}
	
}
