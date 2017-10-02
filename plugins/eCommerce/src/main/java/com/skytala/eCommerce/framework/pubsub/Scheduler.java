package com.skytala.eCommerce.framework.pubsub;

import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler {

	//Singleton
	private static final Scheduler instance = new Scheduler();
	
	private LinkedBlockingQueue<Command> queue = new LinkedBlockingQueue<Command>();

	public Scheduler schedule(Command com) {
		queue.add(com);
		return instance;
	}
	
	public static Scheduler instance() {
		return instance;
	}
	
	public static EventAccessor execute(Command command) throws Exception {
		Thread thread = new Thread(command);
		thread.start();
		return new EventAccessor(command, thread);
	}
	
	public static QueryDataAccessor execute(Query query) throws Exception {
		Thread thread = new Thread(query);
		thread.start();
		return new QueryDataAccessor(query, thread);
	}
	
	//____________________
public void executeNext() throws Exception {
		
		if(!queue.isEmpty()) {
			try {
				queue.take().execute();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			throw new Exception("Error: Queue is empty!");
		}
	}
	
	public void executeAll() throws Exception {
		for(int i = 0; i < queue.size(); i++) {
			queue.take().execute();
		}
	}
	
}


