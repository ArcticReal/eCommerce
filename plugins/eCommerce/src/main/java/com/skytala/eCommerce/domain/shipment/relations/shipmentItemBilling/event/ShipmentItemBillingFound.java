package com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.model.ShipmentItemBilling;
public class ShipmentItemBillingFound implements Event{

	private List<ShipmentItemBilling> shipmentItemBillings;

	public ShipmentItemBillingFound(List<ShipmentItemBilling> shipmentItemBillings) {
		this.shipmentItemBillings = shipmentItemBillings;
	}

	public List<ShipmentItemBilling> getShipmentItemBillings()	{
		return shipmentItemBillings;
	}

}
