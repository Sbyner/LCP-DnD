package mechanics.effects;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import creatures.Creature;
import mechanics.Action;

public abstract class Effect {

	boolean over = false;

	public void tick() {
	}

	public boolean isOver() {
		return over;
	}
	
	public void setOver() {
		over = true;
	}
	
	public void onStartOfTurn(Creature creature, Action act) {
		
	}
	
	public void onAttack(Creature creature, Action act) {
		
	}
	
	public void onCast(Creature creature, Action act) {
		
	}

	public void onEndOfTurn(Creature creature, Action act) {
	}
}



