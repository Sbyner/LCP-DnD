package mechanics.effects;

import java.util.function.BiConsumer;

import creatures.Creature;
import mechanics.Action;

public abstract class Effect implements BiConsumer<Creature,Action>{
	onEvent trigger;
	public void tick() {}

	public enum onEvent {
		STARTOFTURN,
		ACTION
	}
}
