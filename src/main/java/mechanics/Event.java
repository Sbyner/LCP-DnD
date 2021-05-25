package mechanics;

import java.util.HashSet;

import creatures.Creature;

public class Event {
	public Event(Creature initiators, String action, Creature target,String additionalInfo) {
		super();
		this.initiator = initiators;
		this.action = action;
		this.additionalInfo = additionalInfo;
		this.target = target;
	}
	public Creature getInitiator() {
		return initiator;
	}
	public String getAction() {
		return action;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public Creature getTarget() {
		return target;
	}
	Creature initiator;
	String action;
	String additionalInfo="";
	Creature target;
	Event previousRelatedEvent;
	public Event getPreviousRelatedEvent() {
		return previousRelatedEvent;
	}
	public void setPreviousRelatedEvent(Event previousRelatedEvent) {
		this.previousRelatedEvent = previousRelatedEvent;
	}
}
