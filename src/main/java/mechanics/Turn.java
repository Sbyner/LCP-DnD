package mechanics;

public class Turn {
	TurnState status = TurnState.INIT;

	public TurnState getStatus() {
		return status;
	}

	public void setStatus(TurnState status) {
		this.status = status;
	}
}
