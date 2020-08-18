package h584969.esc;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class SystemClass {
	ArrayList<ComponentClass> activeComponents;
	HashMap<Long,Integer> indexMap;
	public SystemClass() {
		activeComponents = new ArrayList<>();
	}
	
	void addComponentToEntity(GameObject target) {
		ComponentClass comp = addComponent(target);
		
		activeComponents.add(comp);
		indexMap.put(target.getID(), activeComponents.size()-1);
	}
	
	void cleanUp() {
		
	}
	
	public abstract ComponentClass addComponent(GameObject target);
	
	
}
