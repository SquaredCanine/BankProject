package nl.project34.henk.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class CheckPinResponse {
	@JsonProperty
	private boolean correct;
	@JsonProperty
	private boolean blocked;

	public CheckPinResponse(){

	}

	public CheckPinResponse(boolean correct){
		this.setCorrect(correct);
	}

	public boolean getCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}