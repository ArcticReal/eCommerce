package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Broker {

	private static final Broker instance = new Broker();
	HashMap<Class<?>, Set<Subscriber>> subscribers = new HashMap<>();

	public static Broker instance() {
		return instance;
	}
	
	public void subscribe(Class<?> topic, Subscriber subscriber) {
		getTopic(topic).add(subscriber);
	}
	
	public void unsubscribe(Class<?> topic, Subscriber subscriber) {
		getTopic(topic).remove(subscriber);
	}
	
	
	public Set<Subscriber> getTopic(Class<?> topic){
		subscribers.computeIfAbsent(topic, k -> new HashSet<>());
		return subscribers.get(topic);
	}
	
	public void publish(Event event) {
		getTopic(event.getClass()).forEach(subscriber -> subscriber.receive(event));
	}
}
