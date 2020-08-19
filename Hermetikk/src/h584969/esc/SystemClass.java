package h584969.esc;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class SystemClass<T extends ComponentClass>{
	ArrayList<T> activeComponents;
	HashMap<Long,Integer> indexMap;
	
	private long systemID;
	
	public SystemClass() {
		activeComponents = new ArrayList<>();
	}
	
	void addComponentToEntity(GameObject target) {
		T comp = addComponent(target);
		
		activeComponents.add(comp);
		indexMap.put(target.getID(), activeComponents.size()-1);
	}
	
	void rinse() {
		
	}
	
	void removeComponent(GameObject target) {
		
	}
	
	
	void setID(long id) {
		systemID = id;
	}
	
	public long getID() {
		return systemID;
	}
	
	public abstract T addComponent(GameObject target);
	
	
}
