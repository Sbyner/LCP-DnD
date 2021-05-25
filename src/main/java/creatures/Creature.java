package creatures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.kie.api.definition.type.PropertyReactive;

import mechanics.Stat;
import mechanics.Utils;
import mechanics.Utils.Advantage;
import mechanics.effects.Effect;
import mechanics.spells.Spell;

@PropertyReactive
public class Creature {

	HashMap<String, Stat<Integer>> stats = new HashMap<String, Stat<Integer>>();
	HashMap<String, Stat<Advantage>> advantage = new HashMap<String, Stat<Advantage>>();
	HashSet<Effect> effects = new HashSet<Effect>();
	ArrayList<Integer> spellSlots = new ArrayList<Integer>(9);

	public HashSet<Effect> getEffects() {
		return effects;
	}

	public Creature() {
		
		level = 10;
		spellSlots = Utils.getSlots(level);

		stats.put("ca", new Stat<Integer>(Integer.valueOf(10)));
		stats.put("strength", new Stat<Integer>(Integer.valueOf(10)));
		stats.put("dexterity", new Stat<Integer>(Integer.valueOf(10)));
		stats.put("constitution", new Stat<Integer>(Integer.valueOf(10)));
		stats.put("intelligence", new Stat<Integer>(Integer.valueOf(10)));
		stats.put("wisdom", new Stat<Integer>(Integer.valueOf(10)));
		stats.put("charisma", new Stat<Integer>(Integer.valueOf(10)));
		stats.put("initiative", new Stat<Integer>(Integer.valueOf(10)));

		advantage.put("ca", new Stat<Advantage>(Advantage.NO));
		advantage.put("strength", new Stat<Advantage>(Advantage.NO));
		advantage.put("dexterity", new Stat<Advantage>(Advantage.NO));
		advantage.put("constitution", new Stat<Advantage>(Advantage.NO));
		advantage.put("intelligence", new Stat<Advantage>(Advantage.NO));
		advantage.put("wisdom", new Stat<Advantage>(Advantage.NO));
		advantage.put("charisma", new Stat<Advantage>(Advantage.NO));
		advantage.put("initiative", new Stat<Advantage>(Advantage.NO));
		advantage.put("hit", new Stat<Advantage>(Advantage.NO));

		maxHp = Utils.roll((20 + getBonus("constitution")) + "d8", Utils.Advantage.NO);
		hp = maxHp;
	}

	int level;
	boolean fought = false;

	public void updateEffects(Set<Effect> newEffects) {
		effects = new HashSet<Effect>(newEffects);
	}
	
	boolean dead = false;
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	String namecode;
	String gender;
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNamecode() {
		return getName().split(" ")[0].trim().toLowerCase();
	}

	public int getMaxHp() {
		return maxHp;
	}

	int hp;

	public int getHp() {
		return hp;
	}

	public boolean slotsAvailable(Spell spell) {
		if (spell.getLevel() != 0)
			return spellSlots.get(spell.getLevel()) > 0;
		else
			return true;
	}

	public void modifyHp(int mod) {
		hp += mod;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void castSpell(Spell spell) {
		if (spell.getLevel() == 0)
			return;
			spellSlots.set(spell.getLevel(), spellSlots.get(spell.getLevel())-1);
	}
	


	int maxHp;

	String damage = "2d10";

	public String getDamage() {
		return damage;
	}

	String name = "UNNAMED";

	public Stat<Integer> getStat(String id) {
		return stats.get(id);
	}

	public Collection<Stat<Integer>> getStats() {
		return stats.values();
	}

	public void setStatPerm(String id, int value) {
		stats.get(id).permModify((x) -> value);
	}

	public void modifyStat(String id, int value) {
		stats.get(id).modify((x) -> value);
	}

	public Stat<Advantage> getAdvantage(String id) {
		return advantage.get(id);
	}

	public Collection<Stat<Advantage>> getAdvantages() {
		return advantage.values();
	}

	public int getBonus(String stat) {
		return Utils.calculateBonus(stats.get(stat).getValue());
	}

	public String getName() {
		return name;
	}

	public boolean isFought() {
		return fought;
	}

	public void setFought(boolean fought) {
		this.fought = fought;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		var res = "\n";
		res += "========================================\n";
		res += name + "\n";
		res += "HP: " + hp + "/" + maxHp + "\n";
		var stats = this.stats.entrySet().stream().sorted(new Comparator<Entry<String, Stat<Integer>>>() {

			@Override
			public int compare(Entry<String, Stat<Integer>> arg0, Entry<String, Stat<Integer>> arg1) {

				return arg0.getKey().compareTo(arg1.getKey());
			}
		}).toArray();
		for (var obj : stats) {
			var entry = (Entry<String, Stat<Integer>>) obj;
			int bonusValue = getBonus(entry.getKey());
			String bonus = bonusValue >= 0 ? "+" + bonusValue : bonusValue + "";
			res += entry.getKey() + ": " + entry.getValue().toString() + "" + bonus + "\n";
		}
		res += "========================================\n";
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creature other = (Creature) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
