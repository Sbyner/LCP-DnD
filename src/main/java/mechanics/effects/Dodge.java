package mechanics.effects;

import creatures.Creature;

public class Dodge extends Effect{
	int durationTurns;
	Dodge(int durationTurns){
		this.durationTurns=durationTurns;
	}
	@Override
	public void tick() {
		durationTurns--;
		
	}

	@Override
	public void accept(Creature arg0) {
		if(durationTurns>0) {
			arg0.
		}
		
	}

}
