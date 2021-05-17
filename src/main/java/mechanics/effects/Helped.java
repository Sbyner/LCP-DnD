package mechanics.effects;

import creatures.Creature;
import mechanics.Action;
import mechanics.Utils;

public class Helped extends Effect {
	public Helped(Creature targetCreature) {
		this.creature=targetCreature;
	}
	
	public void onAttack(Creature creature, Action act) {
		System.out.println("IN HELP APPLICATION: "+creature.getName()+" - "+act.getTargetCreature().getName());
		if(creature.equals(act.getOriginCreature())) {
			act.getOriginCreature().getAdvantage("hit").modify((x)->Utils.increaseAdvantage(x));
			System.out.println("HELP APPLIED");
			setOver();
		}
		
	}
}
