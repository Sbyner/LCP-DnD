package mechanics.effects;

import creatures.Creature;
import mechanics.Action;
import mechanics.Utils.Advantage;

public class Dodge extends Effect{
	
	@Override
	public void tick() {
		setOver();
		
	}

	@Override
	public void onAttack(Creature creature, Action act) {
		if(creature.equals(act.getTargetCreature())) {
			act.getOriginCreature().getAdvantage("hit").modify((x)->Advantage.DISADVANTAGE);
		}
	}




}
