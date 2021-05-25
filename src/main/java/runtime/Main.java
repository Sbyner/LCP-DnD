package runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.lang.Integer;

import org.jpl7.*;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import creatures.Creature;
import mechanics.AttackWanderFeeder;
import mechanics.kie.KieSessionFactory;
import mechanics.narrators.ASTROGENNarrator;
import mechanics.telegram.Bot;

/**
 * This is a sample class to launch a rule.
 */
public class Main {

	public static final void main(String[] args) {
        try {
            
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	// load up the knowledge base
	        
        	KieSession kSession = KieSessionFactory.getSession("ksession-rules");

            // go !
        	kSession.addEventListener(new RuleRuntimeEventListener() {
				
				@Override
				public void objectUpdated(ObjectUpdatedEvent arg0) {
					System.out.println("Updated: "+arg0.getObject().toString());
					
				}
				
				@Override
				public void objectInserted(ObjectInsertedEvent arg0) {
					System.out.println("Inserted: "+arg0.getObject().toString());
					
				}
				
				@Override
				public void objectDeleted(ObjectDeletedEvent arg0) {
					System.out.println("Deleted: "+arg0.getOldObject().toString());
					
				}
			});
        	
        	
        	
        	var wander = new Creature();
            wander.setName("Wander Hawke");
            wander.setGender("mask");
            var velthal = new Creature();
            velthal.setName("Velthal");
            velthal.setGender("mask");
            var elsa = new Creature();
            elsa.setName("Elsa");
            elsa.setGender("fem");
            elsa.setHp(35);

            

            List<Creature> creatures = new ArrayList<Creature>();
            creatures.add(wander);
            creatures.add(velthal);
            //creatures.add(elsa);

            var fight = new Fight(kSession,creatures);
            
            fight.setCreatures(creatures);
        	
        	
        	fight.setSession(kSession);
        	
        	var narrator = new ASTROGENNarrator(creatures);
        	
        	//fight.setup();
        	//fight.run();
        	
        	var tbot = new TelegramBot("1866795805:AAGQbwppQDp00FAj28YECtwzmen0gr1ZSrk");
        	
            Bot bot = new Bot(tbot,fight,narrator);
            
        	tbot.setUpdatesListener(updates -> {
        		
        		var id = updates.stream().map((update)->{
        			Consumer<Update> consoom = bot.getUpdateHandler();
        			try { consoom.accept(update);}
        			catch (Exception e) {
						e.printStackTrace();
					}
        			return update.updateId();
        			}).reduce(-1, (x,y)->y);
        	    // ... process updates
        	    // return id of last processed update or confirm them all
        	    return id;
        	});
        	
            
            // paraphrase(f(pres,isa,john,subscriber) & f(pres,isa,mary,subscriber) & f(pres,state,john,busy) & f(pres,state,mary,idle)).
            System.out.println(narrator.getCharacterStatus(creatures));        
            
            //Query q2 = new Query("deep(f(pres,isa,john,subscriber) & f(pres,isa,mary,subscriber) & f(pres,state,john,busy) & f(pres,state,mary,idle), X).");
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }



}
