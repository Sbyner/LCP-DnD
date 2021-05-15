package mechanics.effects;

import java.util.function.Consumer;

import mechanics.Action;

public abstract class Effect implements Consumer<Action> {
	onEvent trigger;
	boolean over = false;

	public void tick() {
	}

	public boolean isOver() {
		return over;
	}
	
	public void setOver() {
		over = true;
	}

	public enum onEvent {
		STARTOFTURN, ACTION
	}
}
