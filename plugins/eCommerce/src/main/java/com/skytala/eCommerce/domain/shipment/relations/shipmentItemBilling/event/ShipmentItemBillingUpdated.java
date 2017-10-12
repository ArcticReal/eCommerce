package com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.model.ShipmentItemBilling;
public class ShipmentItemBillingUpdated implements Event{

	private boolean success;

	public ShipmentItemBillingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
