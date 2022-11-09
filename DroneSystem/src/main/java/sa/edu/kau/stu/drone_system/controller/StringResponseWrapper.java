package sa.edu.kau.stu.drone_system.controller;

import javax.xml.ws.Response;

public class StringResponseWrapper {
	private String response;
	
	public StringResponseWrapper() {
		
	}
	
	public StringResponseWrapper(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
