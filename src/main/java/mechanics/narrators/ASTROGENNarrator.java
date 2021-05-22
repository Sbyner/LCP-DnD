package mechanics.narrators;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import creatures.Creature;
import mechanics.Event;
import mechanics.Utils;

public class ASTROGENNarrator implements Narrator {

	public ASTROGENNarrator(List<Creature> characters) {
		String thread = Thread.currentThread().getName();
		Query.hasSolution("use_module(library(jpl))"); // only because we call e.g. jpl_pl_syntax/1 below
		Term swi = Query.oneSolution("current_prolog_flag(version_data,Swi)").get("Swi");
		System.out.println("swipl.version = " + swi.arg(1) + "." + swi.arg(2) + "." + swi.arg(3));
		System.out.println("swipl.syntax = " + Query.oneSolution("jpl_pl_syntax(Syntax)").get("Syntax"));
		System.out.println("swipl.home = " + Query.oneSolution("current_prolog_flag(home,Home)").get("Home").name());
		System.out.println("jpl.jar = " + JPL.version_string());
		System.out.println("jpl.dll = " + org.jpl7.fli.Prolog.get_c_lib_version());
		System.out.println("jpl.pl = " + Query.oneSolution("jpl_pl_lib_version(V)").get("V").name());

//		Query dynamicNames = new Query("dynamic propernoun/3.");
//		System.out.println("dynamic propernoun/3 " + (dynamicNames.hasSolution() ? "succeeded" : "failed"));
//		dynamicNames.close();

		characters.forEach(this::declareCreature);

//		Query compile = new Query("compile_predicates([propernoun/3]).");
//		System.out.println("compile_predicates([propernoun/3]) " + (compile.hasSolution() ? "succeeded" : "failed"));
//		compile.close();

		Query q1 = new Query("consult", new Term[] { new Atom("src/main/resources/root.pl") });
		System.out.println("consult ASTROGEN " + (q1.hasSolution() ? "succeeded" : "failed"));

		q1.close();

		Query init = new Query("all_rules.");
		System.out.println("all_rules " + (init.hasSolution() ? "succeeded" : "failed"));
		init.close();

		Query commas = new Query("clause_comma.");
		System.out.println("clause_comma " + (commas.hasSolution() ? "succeeded" : "failed"));
		commas.close();

		Query pronouns = new Query("pronoun.");
		System.out.println("pronoun " + (pronouns.hasSolution() ? "succeeded" : "failed"));
		pronouns.close();

		// Query reconsult = new Query("reconsult", new
		// Atom("src/resources/astrogen/lexicon"));
		// System.out.println("reconsult " + (reconsult.hasSolution() ? "succeeded" :
		// "failed"));
		// reconsult.close();
	}

	public String apply(Event arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	private String fify(String tense, String rel, String subject, String object) {
		return "f(" + tense + "," + rel + "," + subject + "," + object + ")";
	}
	
	private String fify(String tense, String rel, String subject, String object, String target) {
		return "f(" + tense + "," + rel + "," + subject + "," + object + ","+target+")";
	}

	public String getCharacterStatus(Collection<Creature> creatures) {
		var comp = new Compound("paraphrase", new Term[] { Term.textToTerm(creatures.stream().map((creature) -> {
			return fify("pres", "state", creature.getNamecode(), Utils.hurtStatus(creature));
		}).collect(Collectors.joining(" & "))) });
		System.out.println(comp);
		return query(comp);
	}

//	private void declareCreature(Creature creature) {
//		var lexiconDef = Term.textToTerm(String.format("propernoun(sing,%s,%s)      --> [%s].", creature.getGender(),
//				creature.getNamecode(), creature.getName().toLowerCase().replace(' ', ',')));
//		Query query = new Query("assert", lexiconDef);
//		System.out.println("Registering " + creature.getName()+":"+creature.getNamecode() + " " + (query.hasSolution() ? "succeeded" : "failed"));
//		query.close();
//	}

	private void declareCreature(Creature creature) {
		var lexiconDef = String.format("propernoun(sing,%s,%s)      --> [%s].", creature.getGender(),
				creature.getNamecode(), creature.getName().toLowerCase().replace(' ', ','));
		// var output = new PrintWriter(new
		// FileWriter("src/main/resources/astrogen/domain", true));
		try {
			var pattern = Pattern.compile("^.+\\(.+,.+,(.+)\\)");
			if (Files.lines(Path.of("src/main/resources/astrogen/domain")).map((line) -> {

				var matcher = pattern.matcher(line);
				if (matcher.lookingAt())
					return matcher.group(1);
				else return "NONDECLARATIONLINE";
			}).noneMatch((x) -> x.equals(creature.getNamecode()))) {

				var output = new PrintWriter(new FileWriter("src/main/resources/astrogen/domain", true));
				output.println(lexiconDef);
				output.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("Registering " +
		// creature.getName()+":"+creature.getNamecode() + " " + (query.hasSolution() ?
		// "succeeded" : "failed"));
	}

	public String query(Term term) {
		Query q2 = new Query("with_output_to",
				new Term[] { new Compound("string", new Term[] { new Variable("X") }), term });
		var res = q2.oneSolution().get("X").toString();
		q2.close();
		return res.substring(1, res.length() - 1);
	}

}
