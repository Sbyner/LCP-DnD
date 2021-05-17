package mechanics;

import java.util.List;

import creatures.Creature;
import mechanics.Action.Type;

public class AttackWanderFeeder implements ActionFeeder {

	@Override
	public Action feed(Creature originCreature, List<Creature> creatures) {
		var act = new Action();
		if (originCreature.getName().equals("Wander Hawke")) {
			act.setOriginCreature(originCreature);
			act.setTargetCreature(originCreature);
			act.setType(Action.Type.DODGE);
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

}
