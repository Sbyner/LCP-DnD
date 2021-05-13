package mechanics;

public class Action {
	String creatureName;
	String targetCreatureName;
	Type type;
	

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getCreatureName() {
		return creatureName;
	}

	public void setCreatureName(String creatureName) {
		this.creatureName = creatureName;
	}
	
	public enum Type{
		ATTACK,
		CAST,
		DODGE,
		HELP,
		HIDE
	}
}
