package com.skytala.eCommerce.domain.party.relations.communicationEventProduct.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.model.CommunicationEventProduct;
public class CommunicationEventProductFound implements Event{

	private List<CommunicationEventProduct> communicationEventProducts;

	public CommunicationEventProductFound(List<CommunicationEventProduct> communicationEventProducts) {
		this.communicationEventProducts = communicationEventProducts;
	}

	public List<CommunicationEventProduct> getCommunicationEventProducts()	{
		return communicationEventProducts;
	}

}
