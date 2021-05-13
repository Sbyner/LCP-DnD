package mechanics.effects;

import creatures.Creature;
import mechanics.Action;

public class Dodge extends Effect{
	int durationTurns;
	
	Dodge(int durationTurns){
		this.durationTurns=durationTurns;
		trigger = onEvent.ACTION;
	}
	@Override
	public void tick() {
		durationTurns--;
		
	}
	@Override
	public void accept(Creature arg0, Action arg1) {
		if(durationTurns>0) {
			
		}
		
	}



}
