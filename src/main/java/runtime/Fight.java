package runtime;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import creatures.Creature;
import mechanics.ActionFeeder;
import mechanics.Turn;
import mechanics.Turn.State;

public class Fight {
	public KieSession getSession() {
		return session;
	}

	public void setSession(KieSession session) {
		this.session = session;
	}

	public List<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(List<Creature> creatures) {
		this.creatures = creatures;
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

	public HashMap<Creature, FactHandle> getHandles() {
		return handles;
	}

	public void setHandles(HashMap<Creature, FactHandle> handles) {
		this.handles = handles;
	}

	public ActionFeeder getFeeder() {
		return feeder;
	}

	public void setFeeder(ActionFeeder feeder) {
		this.feeder = feeder;
	}

	KieSession session;
	List<Creature> creatures;
	Turn turn = new Turn();
	HashMap<Creature, FactHandle> handles = new HashMap<Creature, FactHandle>();
	ActionFeeder feeder;
	FactHandle turnHandle;
	
	public void setup() {
		
		turnHandle = session.insert(turn);
		for (var creature : creatures) {
			handles.put(creature, session.insert(creature));
		}
		session.fireAllRules();
		creatures.sort(new Comparator<Creature>() {
			@Override
			public int compare(Creature arg0, Creature arg1) {
				return arg1.getStat("initiative").getValue()- arg0.getStat("initiative").getValue();
			}
		});
		
	}
	
	public void run() {
		boolean running = true;
		while(running) {
			for(var creature: creatures) {
				System.out.println(creature.getName()+" PORCODDIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
				creature.setFought(false);
				turn.setStatus(State.TURNSTART);
				session.update(turnHandle, turn);
				session.update(handles.get(creature), creature);
				var act = feeder.feed(creature, creatures);
				var actHandle = session.insert(act);
				session.fireAllRules();
				session.delete(actHandle);
				
			}
		}
	}
}
