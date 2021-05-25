package mechanics.spells;

public class SpellFactory {
	public static Spell getSpell(String name) {
		switch(name) {
		case "magic_missile": return new MagicMissile();
		case "phantasmal_killer": return new PhantasmalKiller();
		default: return new Fumble();
		}
		
	}
}
