package com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.model.ShipmentReceiptRole;
public class ShipmentReceiptRoleDeleted implements Event{

	private boolean success;

	public ShipmentReceiptRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
