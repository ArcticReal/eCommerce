package com.skytala.skytalaPlugin.command;

import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler {

	//Singleton
	private static final Scheduler instance = new Scheduler();
	
	private LinkedBlockingQueue<Event> queue = new LinkedBlockingQueue<Event>();


	public Scheduler schedule(Event event) {
		queue.add(event);
		
		return instance;
	}
	
	
	public void executeNext() throws Exception {
		
		if(!queue.isEmpty()) {
			try {
				queue.take().execute();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}else {

			throw new Exception("Error: Queue is empty!");
		}
		
	}
	
	public void executeAll() throws InterruptedException {
		for(int i = 0; i < queue.size(); i++) {
			queue.take().execute();
		}
	}
	
	public static Scheduler instance() {
		return instance;
		
	}
	
}
