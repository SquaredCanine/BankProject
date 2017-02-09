package nl.project34.henk.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class CheckPasResponse {
	@JsonProperty
	private boolean doesExist;
	@JsonProperty
	private boolean blocked;
	
	public CheckPasResponse(){
		
	}
	
	public CheckPasResponse(boolean doesExist){
		this.setDoesExist(doesExist);
	}

	public boolean doesExist() {
		return doesExist;
	}

	public void setDoesExist(boolean doesExist) {
		this.doesExist = doesExist;
	}

	public boolean isBlocked(){
		return this.blocked;
	}

	public void setBlocked(boolean blocked){
		this.blocked = blocked;
	}
}
