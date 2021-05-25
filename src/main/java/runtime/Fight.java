package runtime;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import creatures.Creature;
import mechanics.Action;
import mechanics.ActionProcessor;
import mechanics.Event;
import mechanics.Turn;
import mechanics.Turn.State;

public class Fight {
	List<Creature> creatures;
	private Iterator<Creature> iter;

	HashMap<Creature, FactHandle> handles = new HashMap<Creature, FactHandle>();
	

	ActionProcessor processor;

	KieSession session;

	Turn turn = new Turn();

	FactHandle turnHandle;

	public Fight(KieSession session, List<Creature> creatures) {
		super();
		this.session = session;
		this.creatures = creatures;
		
		
		turnHandle = session.insert(turn);
		for (var creature : creatures) {
			handles.put(creature, session.insert(creature));
		}
		session.fireAllRules();
		creatures.sort(new Comparator<Creature>() {
			@Override
			public int compare(Creature arg0, Creature arg1) {
				return arg1.getStat("initiative").getValue() - arg0.getStat("initiative").getValue();
			}
		});
		this.iter = this.creatures.iterator();
	}

	public List<Creature> getCreatures() {
		return creatures;
	}

	public ActionProcessor getFeeder() {
		return processor;
	}

	public HashMap<Creature, FactHandle> getHandles() {
		return handles;
	}

	public KieSession getSession() {
		return session;
	}

	public Turn getTurn() {
		return turn;
	}

	public boolean isOver() {
		return !(creatures.stream().filter((x) -> !x.isDead()).count() > 1);
	}
	public void run() {
		while (creatures.stream().filter((x) -> !x.isDead()).count() > 1) {
			for (var creature : creatures) {
				if (!creature.isDead()) {
					creature.setFought(false);
					turn.setStatus(State.TURNSTART);
					session.update(turnHandle, turn);
					session.update(handles.get(creature), creature);
					var act = processor.feed(creature, creatures);
					processor.processAction(act);
					var actHandle = session.insert(act);
					session.fireAllRules();
					session.delete(actHandle);
				}
			}
		}
	}
	public void setCreatures(List<Creature> creatures) {
		this.creatures = creatures;
	}
	public void setFeeder(ActionProcessor feeder) {
		this.processor = feeder;
	}
	public void setHandles(HashMap<Creature, FactHandle> handles) {
		this.handles = handles;
	}

	public void setSession(KieSession session) {
		this.session = session;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

	public void setup() {

	}
	
	public Creature next() {
		if(iter.hasNext()) return  iter.next();
		else {
			iter = creatures.iterator();
			return iter.next();
		}
	}

	public List<Event> step(Creature creature, Action action) {
		if (!creature.isDead()) {
			creature.setFought(false);
			turn.setStatus(State.TURNSTART);
			session.update(turnHandle, turn);
			session.update(handles.get(creature), creature);
			var actHandle = session.insert(action);
			session.fireAllRules();
			session.delete(actHandle);
		}
		
		return action.getEvents();
		
	}
}
