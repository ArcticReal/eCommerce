package com.skytala.eCommerce.domain.party.relations.communicationEventProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.model.CommunicationEventProduct;
public class CommunicationEventProductDeleted implements Event{

	private boolean success;

	public CommunicationEventProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
