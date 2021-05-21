package mechanics.kie;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class KieSessionFactory {
	
	public static KieSession getSession(String id) {

		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    return kContainer.newKieSession(id);
	}
}
