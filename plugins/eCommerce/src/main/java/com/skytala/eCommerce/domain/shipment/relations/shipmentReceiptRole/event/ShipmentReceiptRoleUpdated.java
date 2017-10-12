package com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.model.ShipmentReceiptRole;
public class ShipmentReceiptRoleUpdated implements Event{

	private boolean success;

	public ShipmentReceiptRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
