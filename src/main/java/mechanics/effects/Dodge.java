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
		System.out.println("IN DODGE APPLICATION: "+creature.getName()+" - "+act.getTargetCreature().getName());
		if(creature.equals(act.getTargetCreature())) {
			act.getOriginCreature().getAdvantage("hit").modify((x)->Utils.decreaseAdvantage(x));
			System.out.println("DODGE APPLIED");
		}
	}
	





}
