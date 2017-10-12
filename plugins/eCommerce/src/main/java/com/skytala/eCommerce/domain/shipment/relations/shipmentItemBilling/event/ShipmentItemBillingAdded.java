package com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.model.ShipmentItemBilling;
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
