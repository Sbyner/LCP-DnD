package mechanics;

import java.util.List;

import creatures.Creature;

public interface ActionProcessor {
	public Action feed(Creature originCreature, List<Creature> creatures);
	public void processAction(Action action);
	
}
