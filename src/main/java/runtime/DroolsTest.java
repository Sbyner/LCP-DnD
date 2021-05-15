package runtime;

import org.kie.api.KieServices;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;

import creatures.Creature;
import mechanics.Action;
import mechanics.Action.Type;
import mechanics.Turn;
import mechanics.Utils;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

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
            var shaw = new Creature();
            shaw.setName("Shaw Penn");
            var elsa = new Creature();
            elsa.setName("Elsa Tearblood");
            var agatha = new Creature();
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
            var act2 = new Action();
        	act2.setOriginCreature(wander);
        	act2.setType(Type.CAST);
        	var fact2 = kSession.insert(act2);
        	
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
