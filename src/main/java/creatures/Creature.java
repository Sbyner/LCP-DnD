package creatures;

import mechanics.Utils.Advantage;;

public class Creature {
	int strength;

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public int getInitiativeBonus() {
		return initiativeBonus;
	}

	public void setInitiativeBonus(int initiativeBonus) {
		this.initiativeBonus = initiativeBonus;
	}

	public Advantage getInitiativeAdvantage() {
		return initiativeAdvantage;
	}

	public void setInitiativeAdvantage(Advantage initiativeAdvantage) {
		this.initiativeAdvantage = initiativeAdvantage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	int dexterity;
	int constitution;
	int intelligence;
	int wisdom;
	int charisma;

	int ca;
	int initiativeBonus=0;
	Advantage initiativeAdvantage = Advantage.NO;
	String name;
	int initiative=-9001;
	boolean fought=false;

	public boolean isFought() {
		return fought;
	}

	public void setFought(boolean fought) {
		this.fought = fought;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

}
