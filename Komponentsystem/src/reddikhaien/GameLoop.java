package reddikhaien;


import reddikhaien.komponentsystem.ComponentSystem;
import reddikhaien.komponentsystem.ComponentSystem.Entry;
import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.event.IDrawable;
import reddikhaien.komponentsystem.event.IEvent;
import reddikhaien.komponentsystem.event.IPreDrawable;
import reddikhaien.komponentsystem.event.ITickable;
import reddikhaien.render.Render;
import reddikhaien.render.Screen;
import reddikhaien.world.World;
import reddikhaien.world.entity.EntityRecipe;
import reddikhaien.world.entity.PlayerEntityRecipe;
import reddikhaien.world.entity.TestEntityRecipe;

public class GameLoop {
	
	private ComponentSystem componentSystem;
	private Entry tickEntry;
	private Entry preDrawEntry;
	private Entry drawEntry;
	
	private Screen screen;
	private Render canvasGraphics;
	private World world;
	public GameLoop() {
		
		this.screen = new Screen();
		this.canvasGraphics = screen.getCanvasGraphics();
		this.componentSystem = new ComponentSystem();
		tickEntry = componentSystem.addEventType(ITickable.class, (IEvent t) ->{
			((ITickable)t).tick();
		});
		drawEntry = componentSystem.addEventType(IDrawable.class, (IEvent d) ->{
			((IDrawable)d).draw(canvasGraphics);
		});
		preDrawEntry = componentSystem.addEventType(IPreDrawable.class, (IEvent p) ->{
			((IPreDrawable)p).preDraw(canvasGraphics);
		});

		canvasGraphics.SetEntry(drawEntry,preDrawEntry);
		
		EntityRecipe testRecipe = new TestEntityRecipe();
		EntityRecipe playerRecipe = new PlayerEntityRecipe();
		
		GameObject player = new GameObject(componentSystem);
		
		testRecipe.BuildEntity(new GameObject(componentSystem));
		playerRecipe.BuildEntity(player);
		
		this.world = new World(player);
	}
	
	public void run() {
		while(true) {
			
			screen.pollInput();
			
			world.tick();
			tickEntry.execute();

			canvasGraphics.draw(world);
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
}
