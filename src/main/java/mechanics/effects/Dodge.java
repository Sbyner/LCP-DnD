package mechanics.effects;

import creatures.Creature;
import mechanics.Action;

public class Dodge extends Effect{
	
	@Override
	public void tick() {
		setOver();
		
	}
	@Override
	public void accept(Action arg1) {
		if(!over) {
			
		}
		
	}



}
