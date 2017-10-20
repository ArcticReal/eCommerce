package com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receipt.ShipmentReceipt;
public class ShipmentReceiptDeleted implements Event{

	private boolean success;

	public ShipmentReceiptDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
