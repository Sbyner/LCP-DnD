package mechanics.effects;

import creatures.Creature;
import mechanics.Action;
import mechanics.Utils;

public class Helped extends Effect {
	public Helped(Creature targetCreature) {
		this.creature=targetCreature;
	}
	
	@Override
	public void onAttack(Creature creature, Action act) {
		if(creature.equals(act.getOriginCreature())) {
			act.getOriginCreature().getAdvantage("hit").modify((x)->Utils.increaseAdvantage(x));
			setOver();
		}
		
	}
}
