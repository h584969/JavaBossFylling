package reddikhaien.komponentsystem;

import java.util.HashMap;
import java.util.Map;

import reddikhaien.komponentsystem.komponent.Component;

public class GameObject {
	private static int IDCounter = 1;
	
	
	private int id;
	private Map<Class<? extends Component>, Component> components;
	private ComponentSystem system;
	
	public GameObject(ComponentSystem system) {
		id = IDCounter++;
		this.system = system;
		this.components = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> type){
		return (T)components.get(type);
	}
	
	public void insertComponent(Component component) {
		if (!components.containsKey(component.getClass())) {
			components.put(component.getClass(), component);
		}
	}
	
	public void addComponent(Class<? extends Component> type) {
		system.addComponent(this, type);
	}
	
	@Override
	public int hashCode() {
		return id;
	}
}
