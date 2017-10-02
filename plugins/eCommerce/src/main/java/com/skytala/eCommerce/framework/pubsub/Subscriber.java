package com.skytala.eCommerce.framework.pubsub;

public interface Subscriber {

	void receive(Event event);
}
