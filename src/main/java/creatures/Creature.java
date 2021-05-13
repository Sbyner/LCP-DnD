package creatures;

import mechanics.Utils;
import mechanics.Utils.Advantage;;

public class Creature {
	
	int ca;

	int charisma;
	
	int charismaBonus;

	int constitution;

	int constitutionBonus;
	
	int dexterity;
	
	int dexterityBonus;

	boolean fought=false;

	int initiative=-9001;

	Advantage initiativeAdvantage = Advantage.NO;

	int initiativeBonus=0;

	int intelligence;

	int intelligenceBonus;

	String name;

	int strength;

	int strengthBonus;

	int wisdom;

	int wisdomBonus;
	
	public int getCa() {
		return ca;
	}

	public int getCharisma() {
		return charisma;
	}

	public int getCharismaBonus() {
		return Utils.calculateBonus(charisma);
	}
	
	public int getConstitution() {
		return constitution;
	}

	public int getConstitutionBonus() {
		return Utils.calculateBonus(constitution);
	}
	
	public int getDexterity() {
		return dexterity;
	}

	public int getDexterityBonus() {
		return Utils.calculateBonus(dexterity);
	}

	public int getInitiative() {
		return initiative;
	}

	public Advantage getInitiativeAdvantage() {
		return initiativeAdvantage;
	}

	public int getInitiativeBonus() {
		return initiativeBonus;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getIntelligenceBonus() {
		return Utils.calculateBonus(intelligence);
	}

	public String getName() {
		return name;
	}

	public int getStrength() {
		return strength;
	}

	public int getStrengthBonus() {
		return Utils.calculateBonus(strength);
	}

	public int getWisdom() {
		return wisdom;
	}
	public int getWisdomBonus() {
		return Utils.calculateBonus(wisdom);
	}
	public boolean isFought() {
		return fought;
	}
	public void setCa(int ca) {
		this.ca = ca;
	}
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	public void setFought(boolean fought) {
		this.fought = fought;
	}
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}
	public void setInitiativeAdvantage(Advantage initiativeAdvantage) {
		this.initiativeAdvantage = initiativeAdvantage;
	}
	public void setInitiativeBonus(int initiativeBonus) {
		this.initiativeBonus = initiativeBonus;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

}
