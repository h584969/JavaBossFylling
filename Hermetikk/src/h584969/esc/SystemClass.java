package h584969.esc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.print.attribute.standard.Copies;

public abstract class SystemClass<T extends ComponentClass> extends Thread{
	private ComponentSystem listener = null;
	ArrayList<T> activeComponents;
	HashMap<Long,Integer> indexMap;
	private long systemID;
	
	
	
	public SystemClass() {
		super();
		activeComponents = new ArrayList<>();
	}
	

	public final void removeComponent(GameObject target) {
		if (!hasComponent(target)) return;
		
		//indeksen til målet
		int targetIndex = indexMap.get(target.getID());
		if (targetIndex == activeComponents.size()-1) {
			activeComponents.remove(targetIndex);
			indexMap.remove(target.getID());
		}
		else {
			GameObject last = activeComponents.get(activeComponents.size()-1).gameObject;
			int lastIndex = indexMap.get(last.getID());
			
			//bytter plass på target og last
			int tempID = lastIndex;
			T tempComp = activeComponents.get(lastIndex);

			activeComponents.set(lastIndex, activeComponents.get(targetIndex)); // target -> bakerst
			activeComponents.set(targetIndex, tempComp); // bakerst -> fram

			lastIndex = targetIndex;
			targetIndex = tempID;
			
			activeComponents.remove(targetIndex);
			indexMap.remove(target.getID());
		}
	}
	public final boolean hasComponent(GameObject target) {
		return indexMap.containsKey(target.getID());
	}
	public final T getComponent(GameObject target) {
		if (!hasComponent(target)) return null;
		return activeComponents.get(indexMap.get(target.getID()));
	}
	
	@Override
	public final void run() {
		System.out.println("system: " + getID() + " har startet");
		start();
	}
	
	
	
	void addComponentToEntity(GameObject target) {
		if (hasComponent(target)) return;
		T comp = addComponent(target);
		
		activeComponents.add(comp);
		indexMap.put(target.getID(), activeComponents.size()-1);
	}
	
	void setListener(ComponentSystem componentSystem) {
		this.listener = componentSystem;
	}
	
	void rinse() {
		
	}
	
	

	void setID(long id) {
		systemID = id;
	}
	
	public final long getID() {
		return systemID;
	}
	
	
	public abstract void start();
	public abstract T addComponent(GameObject target);
	
	
}
