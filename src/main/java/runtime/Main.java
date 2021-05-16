package runtime;


import java.util.stream.Collectors;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;

import creatures.Creature;
import mechanics.Action;
import mechanics.Action.Type;
import mechanics.kie.KieSessionFactory;
import mechanics.Turn;
import mechanics.Utils;
import mechanics.effects.Effect;

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
        	

        	var turn = new Turn();
            var wander = new Creature();
            wander.setName("Wander Hawke");
            var velthal = new Creature();
            velthal.setName("Velthal");
            Creature shaw = new Creature();
            shaw.setName("Shaw Penn");
            Creature elsa = new Creature();
            elsa.setName("Elsa Tearblood");
            Creature agatha = new Creature();
            agatha.setName("Agatha");
            kSession.insert(turn);
            kSession.insert(wander);
            kSession.insert(velthal);
            kSession.insert(shaw);
            kSession.insert(elsa);
            kSession.insert(agatha);
            kSession.fireAllRules();
            
            
            
            QueryResults results = kSession.getQueryResults("getCreatures");
            results.forEach(row->{
            	Creature creature = (Creature) row.get("$result");
            	System.out.println(creature.getName()+": "+creature.getStat("initiative"));
            });
            for(int i = 0;i<10;i++) {
            Action act2 = new Action();
        	act2.setOriginCreature(wander);
        	act2.setType(Type.CAST);
        	FactHandle fact2 = kSession.insert(act2);
        	
        	kSession.fireAllRules();
            }
        	
//          int counter =0;
//            while(true) {
//            	System.out.println(counter++);
//            	
//            	var act = new Action();
//            	act.setCreatureName("Velthal");
//            	act.setType(Type.ATTACK);
//            	var fact = kSession.insert(act);
//            	
//            	var act2 = new Action();
//            	act2.setCreatureName("Wander Hawke");
//            	act2.setType(Type.CAST);
//            	var fact2 = kSession.insert(act2);
//            	
//            	kSession.fireAllRules();
//            	
//            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }



}
