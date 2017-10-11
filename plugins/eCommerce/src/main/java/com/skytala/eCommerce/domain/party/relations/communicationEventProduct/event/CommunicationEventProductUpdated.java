package com.skytala.eCommerce.domain.party.relations.communicationEventProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.model.CommunicationEventProduct;
public class CommunicationEventProductUpdated implements Event{

	private boolean success;

	public CommunicationEventProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
