package h584969.esc;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class SystemClass<T extends ComponentClass> implements Runnable{
	ArrayList<T> activeComponents;
	HashMap<Long,Integer> indexMap;
	
	
	
	private long systemID;
	
	public SystemClass() {
		activeComponents = new ArrayList<>();
	}
	
	void addComponentToEntity(GameObject target) {
		if (hasComponent(target)) return;
		T comp = addComponent(target);
		
		activeComponents.add(comp);
		indexMap.put(target.getID(), activeComponents.size()-1);
	}
	
	void rinse() {
		
	}
	
	public void removeComponent(GameObject target) {
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
	
	public boolean hasComponent(GameObject target) {
		return indexMap.containsKey(target.getID());
	}
	
	public T getComponent(GameObject target) {
		if (!hasComponent(target)) return null;
		return activeComponents.get(indexMap.get(target.getID()));
	}
	
	@Override
	public void run() {
		System.out.println("System: " + systemID + " har startet");
		this.start();
	}
	
	protected abstract void start();
	
	
	void setID(long id) {
		systemID = id;
	}
	
	public final long getID() {
		return systemID;
	}
	
	public abstract T addComponent(GameObject target);
	
	
}
