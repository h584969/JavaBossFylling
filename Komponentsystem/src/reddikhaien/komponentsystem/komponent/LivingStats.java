package reddikhaien.komponentsystem.komponent;

import reddikhaien.komponentsystem.GameObject;
import reddikhaien.komponentsystem.event.ITickable;

public class LivingStats extends Component implements ITickable{

	
	public static final float MAX_HUNGER = 20.0f;
	public static final float MAX_THIRST = 20.0f;
	public static final float MAX_STAMINA = 20.0f;
	
	public static final float BASE_STAMINA_USAGE = 0.0001f;
	
	public static final float BASE_STAMINA_REGAIN = 0.01f;
	
	public static final float BASE_HUNGER_PER_STAMINA = 0.1f;
	public static final float BASE_THIRST_PER_STAMINA = 0.2f;
	
	public static final float MIN_HUNGER = 4.0f;
	public static final float MIN_THIRST = 5.0f;
	
	private float hunger;
	private float thirst;
	private float stamina;
	
	private float staminaUsage;
	
	public LivingStats(GameObject object) {
		super(object);
		hunger = MAX_HUNGER;
		thirst = MAX_THIRST;
		stamina = MAX_STAMINA;
		staminaUsage = BASE_STAMINA_USAGE;
	}


	@Override
	public void tick() {
		
		stamina -= staminaUsage;
		
		if (stamina < MAX_STAMINA-BASE_STAMINA_REGAIN) {
			
			if (hunger > MIN_HUNGER && thirst > MIN_THIRST) {
				stamina += BASE_STAMINA_REGAIN;
				hunger -= BASE_HUNGER_PER_STAMINA*BASE_STAMINA_REGAIN;
				thirst -= BASE_THIRST_PER_STAMINA*BASE_STAMINA_REGAIN;
			}
		}
	}
	
	public boolean needsFoodAndDrink() {
		return hunger < MIN_HUNGER || thirst < MIN_THIRST;
	}

	public float consumeFood(float amt) {
		hunger += amt;
		
		if (hunger > MAX_HUNGER) {
			float diff = hunger - MAX_HUNGER;
			hunger = MAX_HUNGER;
			return diff;
		}
		return 0.0f;
	}
	
	public float consumeDrink(float amt) {
		thirst += amt;
		if (thirst > MAX_THIRST) {
			float diff = thirst - MAX_THIRST;
			thirst = MAX_THIRST;
			return diff;
		}
		return 0.0f;
	}
	
	public boolean useEnergy(float amt) {
		if (stamina < amt) return false;
		
		stamina -= amt;
		return true;
	}


	public boolean needsFood() {
		return hunger < MIN_HUNGER;
	}
	
	public boolean needsDrink() {
		return thirst < MIN_THIRST;
	}
}
