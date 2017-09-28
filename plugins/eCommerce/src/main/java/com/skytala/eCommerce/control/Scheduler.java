package com.skytala.eCommerce.control;

import java.util.concurrent.LinkedBlockingQueue;

import com.skytala.eCommerce.command.Command;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class Scheduler {

	//Singleton
	private static final Scheduler instance = new Scheduler();
	
	private LinkedBlockingQueue<Command> queue = new LinkedBlockingQueue<Command>();


	public Scheduler schedule(Command com) {
		queue.add(com);
		
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
	
	public void executeAll() throws Exception {
		for(int i = 0; i < queue.size(); i++) {
			queue.take().execute();
		}
	}
	
	public static Scheduler instance() {
		return instance;
		
	}
	
}
