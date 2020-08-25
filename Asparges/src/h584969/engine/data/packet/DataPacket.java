package h584969.engine.data.packet;


import java.util.ArrayList;

public class DataPacket <T extends IPacket<T>> {
	private ArrayList<T> data = new ArrayList<>();
	private long[] ids = null;
	
	public void addData(T data) {
		if (data != null) {
			this.data.add(data.createCopy());
		}
		else {
			this.data.add(null);
		}
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
