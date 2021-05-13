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

import creatures.Creature;
import mechanics.Action;
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
					System.out.println(arg0);
					
				}
				
				@Override
				public void objectInserted(ObjectInsertedEvent arg0) {
					System.out.println(arg0);
					
				}
				
				@Override
				public void objectDeleted(ObjectDeletedEvent arg0) {
					System.out.println(arg0);
					
				}
			});
        	

            var turn = new Turn();
            var creature1 = new Creature();
            creature1.setName("Wander Hawke");
            var creature2 = new Creature();
            creature2.setName("Velthal");
            var creature3 = new Creature();
            creature3.setName("Shaw Penn");
            var creature4 = new Creature();
            creature4.setName("Elsa Tearblood");
            var creature5 = new Creature();
            creature5.setName("Agatha");
            kSession.insert(turn);
            kSession.insert(creature1);
            kSession.insert(creature2);
            kSession.insert(creature3);
            kSession.insert(creature4);
            kSession.insert(creature5);
            kSession.fireAllRules();
            
            int counter =0;
            while(true) {
            	var act = new Action();
            	act.setCreatureName("Velthal");
            	var fact = kSession.insert(act);
            	System.out.println(counter++);;
            	var act2 = new Action();
            	act2.setCreatureName("Wander Hawke");
            	var fact2 = kSession.insert(act2);
            	kSession.fireAllRules();
            	
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }



}
