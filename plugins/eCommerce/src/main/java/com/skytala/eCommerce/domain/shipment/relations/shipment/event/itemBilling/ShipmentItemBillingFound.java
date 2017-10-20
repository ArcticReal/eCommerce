package com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemBilling;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemBilling.ShipmentItemBilling;
public class ShipmentItemBillingFound implements Event{

	private List<ShipmentItemBilling> shipmentItemBillings;

	public ShipmentItemBillingFound(List<ShipmentItemBilling> shipmentItemBillings) {
		this.shipmentItemBillings = shipmentItemBillings;
	}

	public List<ShipmentItemBilling> getShipmentItemBillings()	{
		return shipmentItemBillings;
	}

}
