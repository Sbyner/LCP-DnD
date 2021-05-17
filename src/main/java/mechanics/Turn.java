package mechanics;

public class Turn {
	State status = State.INIT;
	public int count =0;

	public State getStatus() {
		return status;
	}

	public void setStatus(State status) {
		this.status = status;
	}
	public enum State {
		INIT,
		TURNSTART,
		FIGHT
	}
}
