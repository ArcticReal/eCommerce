package com.skytala.eCommerce.domain.shipment.relations.delivery.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.delivery.model.Delivery;
public class DeliveryDeleted implements Event{

	private boolean success;

	public DeliveryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
