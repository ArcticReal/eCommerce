package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.product.CommunicationEventProduct;
public class CommunicationEventProductUpdated implements Event{

	private boolean success;

	public CommunicationEventProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
