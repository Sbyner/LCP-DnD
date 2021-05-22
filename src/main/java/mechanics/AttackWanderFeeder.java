package mechanics;

import java.util.List;

import creatures.Creature;
import mechanics.Action.Type;

public class AttackWanderFeeder implements ActionProcessor {

	@Override
	public Action feed(Creature originCreature, List<Creature> creatures) {
		var act = new Action();
		if (originCreature.getName().equals("Wander Hawke")) {
			act.setOriginCreature(originCreature);
			act.setTargetCreature(creatures.stream().filter((x) -> {
				return x.getName().equals("Velthal");
			}).findAny().get());
			act.setType(Action.Type.CAST);
			act.setArgs("Phantasmal Killer");
		} else if(originCreature.getName().equals("Agatha")) {
			act.setOriginCreature(originCreature);
			act.setTargetCreature(creatures.stream().filter((x) -> {
				return x.getName().equals("Velthal");
			}).findAny().get());
			act.setType(Type.HELP);
		} else {
			act.setOriginCreature(originCreature);
			act.setTargetCreature(creatures.stream().filter((x) -> {
				return x.getName().equals("Wander Hawke");
			}).findAny().get());
			act.setType(Action.Type.ATTACK);
		}
		return act;
	}

	@Override
	public void processAction(Action action) {
		System.out.println(action);
		
	}
	
	

}
