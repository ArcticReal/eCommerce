package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.ItemIssuance;
public class ItemIssuanceUpdated implements Event{

	private boolean success;

	public ItemIssuanceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
