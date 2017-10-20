package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.product.CommunicationEventProduct;
public class CommunicationEventProductFound implements Event{

	private List<CommunicationEventProduct> communicationEventProducts;

	public CommunicationEventProductFound(List<CommunicationEventProduct> communicationEventProducts) {
		this.communicationEventProducts = communicationEventProducts;
	}

	public List<CommunicationEventProduct> getCommunicationEventProducts()	{
		return communicationEventProducts;
	}

}
