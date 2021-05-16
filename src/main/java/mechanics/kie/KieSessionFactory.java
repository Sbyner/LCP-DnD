package mechanics.kie;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class KieSessionFactory {
	
	static KieServices ks = KieServices.Factory.get();
    static KieContainer kContainer = ks.getKieClasspathContainer();
	
	public static KieSession getSession(String id) {
		return kContainer.newKieSession(id);
	}
}
