package mechanics;

import java.util.LinkedList;

import org.drools.core.command.runtime.AddEventListenerCommand;

import creatures.Creature;

public class Action {
	Creature originCreature;
	Creature targetCreature;
	Type type;
	String args;
	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	LinkedList<Event> events = new LinkedList<Event>();
	

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public LinkedList<Event> getEvents() {
		return events;
	}

	public void setOriginCreature(Creature originCreature) {
		this.originCreature = originCreature;
	}

	public void setTargetCreature(Creature targetCreature) {
		this.targetCreature = targetCreature;
	}

	public Creature getOriginCreature() {
		return originCreature;
	}

	public Creature getTargetCreature() {
		return targetCreature;
	}

	public void addEvent(Creature initiator, String action, Creature target, String additionalInfo) {
		events.add(new Event(initiator,action,target,additionalInfo));
	}
	

	public enum Type{
		ATTACK,
		CAST,
		DODGE,
		HELP,
		HIDE
	}
	
	@Override
	public String toString() {
		String res="";
		res+=originCreature.getName()+" "+type.name()+" "+targetCreature.getName();
		return res;
	}
}
