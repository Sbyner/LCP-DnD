package mechanics;

import java.util.LinkedList;

import creatures.Creature;

public class Action {
	Creature originCreature;
	Creature targetCreature;
	Type type;
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
