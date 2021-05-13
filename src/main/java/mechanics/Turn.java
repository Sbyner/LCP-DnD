package mechanics;

public class Turn {
	State status = State.INIT;

	public State getStatus() {
		return status;
	}

	public void setStatus(State status) {
		this.status = status;
	}
	public enum State {
		INIT,
		FIGHT
	}
}
