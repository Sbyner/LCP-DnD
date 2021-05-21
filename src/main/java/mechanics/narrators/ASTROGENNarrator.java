package mechanics.narrators;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import mechanics.Event;

public class ASTROGENNarrator implements Narrator {
	
	public ASTROGENNarrator() {
	Query.hasSolution("use_module(library(jpl))"); // only because we call e.g. jpl_pl_syntax/1 below
    Term swi = Query.oneSolution("current_prolog_flag(version_data,Swi)").get("Swi");
    System.out.println("swipl.version = " + swi.arg(1) + "." + swi.arg(2) + "." + swi.arg(3));
    System.out.println("swipl.syntax = " + Query.oneSolution("jpl_pl_syntax(Syntax)").get("Syntax"));
    System.out.println("swipl.home = " + Query.oneSolution("current_prolog_flag(home,Home)").get("Home").name());
    System.out.println("jpl.jar = " + JPL.version_string());
    System.out.println("jpl.dll = " + org.jpl7.fli.Prolog.get_c_lib_version());
    System.out.println("jpl.pl = " + Query.oneSolution("jpl_pl_lib_version(V)").get("V").name());
    
    Query q1 = 
    	    new Query( 
    		"consult", 
    		new Term[] {new Atom("src/main/resources/root.pl")} 
    	    );
    System.out.println( "consult ASTROGEN " + (q1.hasSolution() ? "succeeded" : "failed"));

    q1.close();
    
    Query init = 
    		new Query("all_rules.");
    System.out.println("all_rules "+ (init.hasSolution() ? "succeeded" : "failed"));
    init.close();
    
    Query commas = 
    		new Query("clause_comma.");
    System.out.println("clause_comma "+ (commas.hasSolution() ? "succeeded" : "failed"));
    commas.close();
    
    Query pronouns = 
    		new Query("pronoun.");
    System.out.println("pronoun "+ (pronouns.hasSolution() ? "succeeded" : "failed"));
    pronouns.close();
	}
    
	
	public String apply(Event arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Term generateTerm(Event ... events) {
		return null;
	}
	
	public String query(Term term) {
		Query q2 = 
        	    new Query( 
        		"with_output_to", 
        		new Term[] {
        			new Compound("string", new Term[] {new Variable("X")}),
        			term
        			});
		var res = q2.oneSolution().get("X").toString();
		q2.close();
		return res.substring(1, res.length()-1);
	}
	
}
