package reddikhaien.komponentsystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import reddikhaien.komponentsystem.event.IEvent;
import reddikhaien.komponentsystem.komponent.Component;

public class ComponentSystem {
	
	//Alle registrerte hendelser
	private Map<Class<? extends IEvent>,Entry> events;
	
	//Alle aktive komponenter
	private Map<Class<? extends Component>,List<Component>> components;
	
	private Set<GameObject> entities;
	
	public ComponentSystem() {
		this.events = new HashMap<>();
		this.components = new HashMap<Class<? extends Component>, List<Component>>();
		this.entities = new HashSet<>();
	}
	
	
	/**
	 * Registrerer en ny hendelsestype i dette systemet
	 * @param eventType typen til hendelsen
	 * @param consumer metode som blir for hver komponent registert på denne hendelsen
	 * @return returnerer et håndteringsobjekt for den nye hendelsen
	 */
	public Entry addEventType(Class<? extends IEvent>eventType, Consumer<IEvent> consumer) {
		if(this.events.containsKey(eventType)) throw new IllegalArgumentException(eventType.getSimpleName() + " eksisterer allerede");
		Entry entry = new Entry(consumer);
		
		events.put(eventType, entry);
		
		return entry;
	}


	
	/**
	 * Legger til en ny komponent av den spesifiserte typen 
	 * @param object objektet som skal få komponenten
	 * @param component komponenttypen som skal brukes
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> T addComponent(GameObject object,Class<T> component) {
		try {
			Constructor<? extends Component> constructor = component.getConstructor(GameObject.class);
			
			Component instance = constructor.newInstance(object);
			
			listComponent(instance);
			
			object.insertComponent(instance);
			
			if (!entities.contains(object)) entities.add(object);
			
			return (T) instance;
		} catch( SecurityException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * Legger til komponenten i systemet og registrerer den til de ulike hendelsene
	 * @param component
	 */
	private void listComponent(Component component) {
		if (!components.containsKey(component.getClass())) {
			components.put(component.getClass(), new ArrayList<>());
		}
		components.get(component.getClass()).add(component);
		
		for (final Class<? extends IEvent> event : events.keySet()) {
			if (event.isAssignableFrom(component.getClass())) {
				events.get(event).components.add(event.cast(component));
			}
		}
	}
	
	
	/**
	 * Returnerer en liste med komponenter av den spesifiserte typen
	 * Returnerer null om det ikke er lagt til noen komponenter av den typen
	 * @param type komponenttypen som skal returneres
	 * @return En liste med komponenter eller null
	 */
	public List<Component> getComponentsOfType(Class<? extends Component> type){
		return components.get(type);
	}
	
	
	public class Entry{
		private Consumer<IEvent> consumer;
		private List<IEvent> components;
		
		public Entry(Consumer<IEvent> consumer) {
			this.consumer = consumer;
			this.components = new ArrayList<IEvent>();
		}
		
		
		
		/**
		 * kjører hendelsen på alle komponentene
		 */
		public void execute() {
			components.forEach((e) ->{
				consumer.accept(e);
			});
		}
	}
}
