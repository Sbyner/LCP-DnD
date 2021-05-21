package runtime;

import java.util.ArrayList;
import java.util.List;

import org.jpl7.*;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;

import creatures.Creature;
import mechanics.AttackWanderFeeder;
import mechanics.kie.KieSessionFactory;
import mechanics.narrators.ASTROGENNarrator;

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
        	
        	var fight = new Fight();
        	
        	var wander = new Creature();
            wander.setName("Wander Hawke");
            var velthal = new Creature();
            velthal.setName("Velthal");

            List<Creature> creatures = new ArrayList<Creature>();
            creatures.add(wander);
            creatures.add(velthal);

            fight.setCreatures(creatures);
        	
        	fight.setFeeder(new AttackWanderFeeder());
        	
        	fight.setSession(kSession);
        	
        	fight.setup();
        	//fight.run();
        	
        	var narrator = new ASTROGENNarrator();
            
            // paraphrase(f(pres,isa,john,subscriber) & f(pres,isa,mary,subscriber) & f(pres,state,john,busy) & f(pres,state,mary,idle)).
            var q2 = 
            	    new Compound( 
            		"paraphrase", 
            		new Term[] {
            				new Compound("&" , new Term[] {
	            				new Compound("f", new Term[] {
	            					new Atom("pres"),
	            					new Atom("isa"),
	            					new Atom("john"),
	            					new Atom("subscriber"),
	            				}),
	            				new Compound("&" , new Term[] {
	    	            				new Compound("f", new Term[] {
	    	            					new Atom("pres"),
	    	            					new Atom("isa"),
	    	            					new Atom("mary"),
	    	            					new Atom("subscriber"),
	    	            				}),
	    	            				new Compound("&" , new Term[] {
	    	    	            				new Compound("f", new Term[] {
	    	    	            					new Atom("pres"),
	    	    	            					new Atom("state"),
	    	    	            					new Atom("john"),
	    	    	            					new Atom("busy"),
	    	    	            				}),
	    	    	            				new Compound("f", new Term[] {
		    	    	            					new Atom("pres"),
		    	    	            					new Atom("state"),
		    	    	            					new Atom("mary"),
		    	    	            					new Atom("busy"),
		    	    	            				}),
	    	            				}),
	            				}),
            				}),
            			});
            				
            		//new Compound("f(pres,isa,john,subscriber) & f(pres,isa,mary,subscriber) & f(pres,state,john,busy) & f(pres,state,mary,idle), X"),
            		
            	
            System.out.println(narrator.query(q2));
            
            
            //Query q2 = new Query("deep(f(pres,isa,john,subscriber) & f(pres,isa,mary,subscriber) & f(pres,state,john,busy) & f(pres,state,mary,idle), X).");
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }



}
