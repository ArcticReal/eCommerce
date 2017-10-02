package com.skytala.eCommerce.framework.pubsub;

public class CommandResult {

	private Event event;
	private Exception exeption;
	
	public CommandResult(Event event) {
		this.event = event;
	}	

	public CommandResult(Exception exeption) {
		this.exeption = exeption; 
	}
	
	public Event event() {
		return event;
	}
	
	public Exception exception() {
		return exeption;
	}
	
	public boolean wasSuccessful() {
		return event == null;
	}

}
