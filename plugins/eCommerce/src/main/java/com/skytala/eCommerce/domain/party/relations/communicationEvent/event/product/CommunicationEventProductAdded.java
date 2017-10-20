package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.product.CommunicationEventProduct;
public class CommunicationEventProductAdded implements Event{

	private CommunicationEventProduct addedCommunicationEventProduct;
	private boolean success;

	public CommunicationEventProductAdded(CommunicationEventProduct addedCommunicationEventProduct, boolean success){
		this.addedCommunicationEventProduct = addedCommunicationEventProduct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommunicationEventProduct getAddedCommunicationEventProduct() {
		return addedCommunicationEventProduct;
	}

}
