package mechanics.effects;

import creatures.Creature;

public class PhantasmalKillerEffect extends Effect {
	int dc;
	int duration = 3;
	public int getDc() {
		return dc;
	}
	@Override
	public void tick() {
		if(duration==0)setOver();
		duration--;
	}
	
	public void setDc(int dc) {
		this.dc = dc;
	}
	public PhantasmalKillerEffect(Creature targetCreature) {
		this.creature=targetCreature;
	}
}
