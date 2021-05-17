package runtime;


import java.util.ArrayList;
import java.util.List;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;

import creatures.Creature;
import mechanics.AttackWanderFeeder;
import mechanics.kie.KieSessionFactory;

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
            Creature shaw = new Creature();
            shaw.setName("Shaw Penn");
            Creature elsa = new Creature();
            elsa.setName("Elsa Tearblood");
            Creature agatha = new Creature();
            agatha.setName("Agatha");
            List<Creature> creatures = new ArrayList<Creature>();
            creatures.add(wander);
            creatures.add(velthal);
            creatures.add(shaw);
            creatures.add(elsa);
            creatures.add(agatha);
        	fight.setCreatures(creatures);
        	
        	fight.setFeeder(new AttackWanderFeeder());
        	
        	fight.setSession(kSession);
        	
        	fight.setup();
        	fight.run();
        	

//        	var turn = new Turn();

//            kSession.insert(turn);
//            var wanderHandle = kSession.insert(wander);
//            var velthalHandle = kSession.insert(velthal);
//            kSession.insert(shaw);
//            kSession.insert(elsa);
//            kSession.insert(agatha);
//            kSession.fireAllRules();
//        	
//
//            
//            QueryResults results = kSession.getQueryResults("getCreatures");
//            results.forEach(row->{
//            	Creature creature = (Creature) row.get("$result");
//            	System.out.println(creature.getName()+": "+creature.getStat("initiative"));
//            });
//            for(int i = 0;i<4;i++) {
//            System.out.println("Turn: "+ ++turn.count);
//            Action act2 = new Action();
//        	act2.setOriginCreature(velthal);
//        	act2.setType(Type.DODGE);
//        	act2.setTargetCreature(wander);
//        	
//        	
//        	
//        	FactHandle fact2 = kSession.insert(act2);
//        	
//        	kSession.fireAllRules();
//        	velthal.setFought(false);
//        	kSession.update(velthalHandle, velthal); 
//        	kSession.delete(fact2);
//            }
//            
//            for (int i = 0; i < 10; i++) {
//            	
//            	Action act2 = new Action();
//            	act2.setOriginCreature(velthal);
//            	act2.setType(Type.DODGE);
//            	act2.setTargetCreature(wander);
//            	
//            	
//            	
//            	FactHandle fact2 = kSession.insert(act2);
//            	
//            	kSession.fireAllRules();
//            	velthal.setFought(false);
//            	kSession.update(velthalHandle, velthal); 
//            	kSession.delete(fact2);
//            	
//            	Action act3 = new Action();
//            	act3.setOriginCreature(wander);
//            	act3.setType(Type.ATTACK);
//            	act3.setTargetCreature(velthal);
//            	System.out.println(wander.getAdvantage("hit"));
//            	FactHandle fact3 = kSession.insert(act3);
//            	
//            	kSession.fireAllRules();
//            	wander.setFought(false);
//            	kSession.update(wanderHandle, wander); 
//            	kSession.delete(fact3);
//            	
//			}
////            Action act2 = new Action();
////        	act2.setOriginCreature(velthal);
////        	act2.setType(Type.ATTACK);
////        	act2.setTargetCreature(wander);
////        	kSession.insert(act2);
////        	kSession.fireAllRules();
//        	
////          int counter =0;
////            while(true) {
////            	System.out.println(counter++);
////            	
////            	var act = new Action();
////            	act.setCreatureName("Velthal");
////            	act.setType(Type.ATTACK);
////            	var fact = kSession.insert(act);
////            	
////            	var act2 = new Action();
////            	act2.setCreatureName("Wander Hawke");
////            	act2.setType(Type.CAST);
////            	var fact2 = kSession.insert(act2);
////            	
////            	kSession.fireAllRules();
////            	
////            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }



}
