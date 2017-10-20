package com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemBilling;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemBilling.ShipmentItemBilling;
public class ShipmentItemBillingDeleted implements Event{

	private boolean success;

	public ShipmentItemBillingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
