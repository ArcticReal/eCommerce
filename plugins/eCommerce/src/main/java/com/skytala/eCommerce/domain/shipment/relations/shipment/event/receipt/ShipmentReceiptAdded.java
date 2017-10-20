package com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receipt.ShipmentReceipt;
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
