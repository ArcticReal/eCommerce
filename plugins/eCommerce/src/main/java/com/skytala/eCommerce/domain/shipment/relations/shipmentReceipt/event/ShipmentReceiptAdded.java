package com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.model.ShipmentReceipt;
public class ShipmentReceiptAdded implements Event{

	private ShipmentReceipt addedShipmentReceipt;
	private boolean success;

	public ShipmentReceiptAdded(ShipmentReceipt addedShipmentReceipt, boolean success){
		this.addedShipmentReceipt = addedShipmentReceipt;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentReceipt getAddedShipmentReceipt() {
		return addedShipmentReceipt;
	}

}
