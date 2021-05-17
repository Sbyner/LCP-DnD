package mechanics;

import java.util.List;

import creatures.Creature;

public interface ActionFeeder {
	public Action feed(Creature originCreature, List<Creature> creatures);
}
