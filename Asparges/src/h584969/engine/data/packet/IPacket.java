package h584969.engine.data.packet;

public interface IPacket<T> {
	public T createCopy();
	public boolean isChanged();
}
