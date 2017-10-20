package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.product.CommunicationEventProduct;
public class CommunicationEventProductDeleted implements Event{

	private boolean success;

	public CommunicationEventProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
