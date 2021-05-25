package mechanics.narrators;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import creatures.Creature;
import mechanics.Event;



public interface Narrator{
	public String narrate(List<Event> events);
	
	public String getCharacterStatus(Collection<Creature> creatures);
}
