package com.skytala.eCommerce.framework.pubsub;

public class QueryDataAccessor {
	

	private Query query;
	private Thread thread;

	public QueryDataAccessor(Query query, Thread thread) {
		this.query = query;
		this.thread = thread;		
	}

	public Event data() throws Exception {	
		thread.join();
		if(query.exception() != null)
			throw query.exception();
		
		return query.data();

	}
	
}
