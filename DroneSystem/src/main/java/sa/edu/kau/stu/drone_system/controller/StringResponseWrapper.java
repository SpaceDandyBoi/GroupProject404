package sa.edu.kau.stu.drone_system.controller;

import javax.xml.ws.Response;

public class StringResponseWrapper {
	private int response;
	
	public StringResponseWrapper() {
		
	}
	
	public StringResponseWrapper(int response) {
		this.response = response;
	}

	public int getResponse() {
		return response;
	}

	public void setResponse(int response) {
		this.response = response;
	}
}
