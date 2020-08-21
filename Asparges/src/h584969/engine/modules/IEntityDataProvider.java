package h584969.engine.modules;

import h584969.engine.data.packet.DataPacket;
import h584969.engine.data.packet.IPacket;

public interface IEntityDataProvider<T extends IPacket<T>> {
	/**
	 * blir kalt når en modul vil kopiere informasjonen til denne modulen
	 * 
	 * @param dataPacker pakkeren som skal ta inn dataen
	 */
	public abstract void copyData(DataPacket<T> dataPacker);
}
