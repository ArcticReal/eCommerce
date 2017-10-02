package com.skytala.eCommerce.framework.pubsub;

public class EventAccessor { 
	
	private Command command;
	private Thread thread;
	
	public EventAccessor(Command command, Thread thread) {
		this.thread = thread;
		this.command = command;
	}


	public Event data() throws Exception {	
		thread.join();
		if(command.exception() != null)
			throw command.exception();
		
		return command.data();

	}
	
}
