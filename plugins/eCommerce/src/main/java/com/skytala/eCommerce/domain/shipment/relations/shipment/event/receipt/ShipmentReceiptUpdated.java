package com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receipt.ShipmentReceipt;
public class ShipmentReceiptUpdated implements Event{

	private boolean success;

	public ShipmentReceiptUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
