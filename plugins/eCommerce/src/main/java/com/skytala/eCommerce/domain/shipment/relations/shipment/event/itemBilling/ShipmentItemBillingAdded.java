package com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemBilling;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemBilling.ShipmentItemBilling;
public class ShipmentItemBillingAdded implements Event{

	private ShipmentItemBilling addedShipmentItemBilling;
	private boolean success;

	public ShipmentItemBillingAdded(ShipmentItemBilling addedShipmentItemBilling, boolean success){
		this.addedShipmentItemBilling = addedShipmentItemBilling;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentItemBilling getAddedShipmentItemBilling() {
		return addedShipmentItemBilling;
	}

}
