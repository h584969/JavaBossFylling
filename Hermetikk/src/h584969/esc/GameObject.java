package h584969.esc;

public final class GameObject {
	private static long IDCounter = 1L;
	private long ObjectID = 0L;
	
	public GameObject() {
		ObjectID = IDCounter++;
	}
	
	
	public long getID(){
		return ObjectID;
	}
}
