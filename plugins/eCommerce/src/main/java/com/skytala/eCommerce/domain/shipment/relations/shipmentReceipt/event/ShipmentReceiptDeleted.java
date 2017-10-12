package com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.model.ShipmentReceipt;
public class ShipmentReceiptDeleted implements Event{

	private boolean success;

	public ShipmentReceiptDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
