package mechanics.effects;

import creatures.Creature;
import mechanics.Action;
import mechanics.Utils.Advantage;

public class Dodge extends Effect{
	
	public Dodge(Creature creature) {
		this.creature = creature;
	}
	
	@Override
	public void tick() {
		setOver();
		
	}

	
	public void onAttack(Creature creature, Action act) {
		System.out.println("IN DODGE APPLICATION: "+creature.getName()+" - "+act.getTargetCreature().getName());
		if(creature.equals(act.getTargetCreature())) {
			act.getOriginCreature().getAdvantage("hit").modify((x)->Advantage.DISADVANTAGE);
			System.out.println("DODGE APPLIED");
		}
	}
	





}
