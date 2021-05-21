package mechanics.spells;

public class SpellFactory {
	public static Spell getSpell(String name) {
		switch(name) {
		case "Magic Missile": return new MagicMissile();
		case "Phantasmal Killer": return new PhantasmalKiller();
		default: return new Fumble();
		}
		
	}
}
