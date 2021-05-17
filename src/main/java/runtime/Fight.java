package runtime;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import creatures.Creature;
import mechanics.ActionFeeder;
import mechanics.Turn;

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

	public void setup() {
		session.insert(turn);
		for (var creature : creatures) {
			handles.put(creature, session.insert(creature));
		}
		session.fireAllRules();
		creatures.sort(new Comparator<Creature>() {
			@Override
			public int compare(Creature arg0, Creature arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		
	}
	
	public void run() {
		boolean running = true;
		while(running) {
			for(var creature: creatures) {
				creature.setFought(false);
				session.update(handles.get(creature), creature);
				var act = feeder.feed(creature, creatures);
				var actHandle = session.insert(act);
				session.fireAllRules();
				session.delete(actHandle);
				
			}
		}
	}
}
