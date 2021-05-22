package mechanics;

import java.util.HashSet;

import creatures.Creature;

public class Event {
	public Event(Creature initiators, String action, Creature target,String additionalInfo) {
		super();
		this.initiators = initiators;
		this.action = action;
		this.additionalInfo = additionalInfo;
		this.target = target;
	}
	Creature initiators;
	String action;
	String additionalInfo="";
	Creature target;
}
