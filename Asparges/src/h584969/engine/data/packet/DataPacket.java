package h584969.engine.data.packet;


import java.util.ArrayList;

public class DataPacket <T extends IPacket<T>> {
	private ArrayList<T> data = new ArrayList<>();
	private long[] ids = null;
	
	public void addData(T data) {
		this.data.add(data.createCopy());
	}
	public void setIds(long[] ids) {
		this.ids = ids;
	}
	public long[] getIds() {
		return ids;
	}
	
	
	public ArrayList<T> getData() {
		return data;
	}
}
