package creatures;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import mechanics.Stat;
import mechanics.Utils;
import mechanics.Utils.Advantage;
import mechanics.effects.Effect;

public class Creature {
	
	HashMap<String, Stat<Integer>> stats = new HashMap<String, Stat<Integer>>();
	HashMap<String, Stat<Advantage>> advantage = new HashMap<String, Stat<Advantage>>();
	HashSet<Effect> effects = new HashSet<Effect>();
	
	public HashSet<Effect> getEffects() {
		return effects;
	}

	public Creature() {
		
		
		
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
		
		maxHp = Utils.roll((20+getBonus("constitution"))+"d8", Utils.Advantage.NO);
		hp = maxHp;
	}
	
	boolean fought=false;
	
	public void updateEffects(Set<Effect> newEffects) {
		effects = new HashSet<Effect>(newEffects);
	}
	int hp;
	int maxHp;
	
	String damage = "2d10";

	String name = "UNNAMED";


	public Stat<Integer> getStat(String id) {
		return stats.get(id);
	}
	
	public Collection<Stat<Integer>> getStats(){
		return stats.values();
	}
	
	public void setStatPerm(String id, int value) {
		stats.get(id).permModify((x)->value);
	}
	
	public void modifyStat(String id, int value) {
		stats.get(id).modify((x)->value);
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
		var res = "";
		res+="========================================\n";
		res+=name+"\n";
		res+="HP: "+hp+"/"+maxHp+"\n";
		var stats= this.stats.entrySet().stream().sorted(new Comparator<Entry<String, Stat<Integer>>>() {

			@Override
			public int compare(Entry<String, Stat<Integer>> arg0, Entry<String, Stat<Integer>> arg1) {
				
				return arg0.getKey().compareTo(arg1.getKey());
			}
		}).toArray();
		for(var entry: stats) {
			res+=((Entry)entry).getKey().toString()+": "+((Entry)entry).getValue().toString()+"\n";
		}
		res+="========================================\n";
		return res;
	}



}
