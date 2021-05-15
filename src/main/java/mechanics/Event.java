package mechanics;

import java.util.HashSet;

import creatures.Creature;

public class Event {
	HashSet<Creature> initiators;
	Action action;
	HashSet<Creature> target;
	Event cause;
}
