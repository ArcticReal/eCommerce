package com.skytala.eCommerce.domain.delivery.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.delivery.model.Delivery;
public class DeliveryAdded implements Event{

	private Delivery addedDelivery;
	private boolean success;

	public DeliveryAdded(Delivery addedDelivery, boolean success){
		this.addedDelivery = addedDelivery;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Delivery getAddedDelivery() {
		return addedDelivery;
	}

}
