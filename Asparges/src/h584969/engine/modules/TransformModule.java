package h584969.engine.modules;

import java.util.HashMap;

import h584969.engine.EntityModule;
import h584969.engine.data.TransformData;

public class TransformModule extends EntityModule{
	//alle transformene i spillet
	HashMap<Long, TransformData> transforms = new HashMap<>();
	
	public TransformModule() {}
	
	//funskjon for å hente ut dataen
	public synchronized void getData(Long[] data,TransformData[] out) {
		for (int i = 0; i < data.length; i++) {
			out[i] = transforms.get(data[i]).createCopy();
		}
	}
	
	public synchronized void setData(Long[] data, TransformData[] in) {
		for (int i = 0; i < data.length; i++) {
			transforms.get(data[i]).setPosition(in[i].getPosition()).setScale(in[i].getScale()).setRotation(in[i].getRotation());
		}
	}
	
	@Override
	public synchronized void addData(Long id) {
		this.addNew(id);
	}
	
	//funskjon for å legge til en ny transform
	public synchronized void addNew(Long id) {
		transforms.put(id,new TransformData());
	}
	
	@Override
	public void run() {}
	
	
	
}
