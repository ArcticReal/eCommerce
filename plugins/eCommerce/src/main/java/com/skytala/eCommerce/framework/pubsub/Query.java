package com.skytala.eCommerce.framework.pubsub;

public abstract class Query implements Runnable{

	private Event resultingData;
	private Exception resultingException;
	private Boolean executed = false;

	public abstract Event execute();
	
	public final void run() {
		try {
			this.resultingData = this.execute();	
		} catch(Exception e) {
			resultingException = e;
		}
		
		this.executed = true;
	}
	
	public final Event data() {
		return resultingData;
	}
	
	public final Boolean isExecuted() {
		return executed;
	}
	
	public final Exception exception() {
		return resultingException;
	}
}
