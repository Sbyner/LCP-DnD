package mechanics.effects;

import creatures.Creature;
import mechanics.Action;
import mechanics.Utils;

public class Dodge extends Effect{
	
	public Dodge(Creature creature) {
		this.creature = creature;
	}
	
	@Override
	public void tick() {
		setOver();
		
	}

	
	@Override
	public void onAttack(Creature creature, Action act) {
		if(creature.equals(act.getTargetCreature())) {
			act.getOriginCreature().getAdvantage("hit").modify((x)->Utils.decreaseAdvantage(x));
		}
	}
	





}
