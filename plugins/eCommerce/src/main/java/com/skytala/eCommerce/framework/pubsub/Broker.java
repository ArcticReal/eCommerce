package com.skytala.eCommerce.framework.pubsub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Broker {

	HashMap<Class<?>, Set<Subscriber>> subscribers = new HashMap<>();

	private static final Broker instance = new Broker();
	public synchronized static Broker instance() {
		return instance;
	}
	
	public synchronized void subscribe(Class<?> topic, Subscriber subscriber) {
		getTopic(topic).add(subscriber);
	}
	
	public synchronized void unsubscribe(Class<?> topic, Subscriber subscriber) {
		getTopic(topic).remove(subscriber);
	}
	
	
	public synchronized Set<Subscriber> getTopic(Class<?> topic){
		subscribers.computeIfAbsent(topic, k -> new HashSet<>());
		return subscribers.get(topic);
	}
	
	public synchronized void publish(Event event) {
		getTopic(event.getClass()).forEach(subscriber -> subscriber.receive(event));
	}
}
