package com.skytala.eCommerce.domain.shipment.relations.shipment.event.receiptRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receiptRole.ShipmentReceiptRole;
public class ShipmentReceiptRoleAdded implements Event{

	private ShipmentReceiptRole addedShipmentReceiptRole;
	private boolean success;

	public ShipmentReceiptRoleAdded(ShipmentReceiptRole addedShipmentReceiptRole, boolean success){
		this.addedShipmentReceiptRole = addedShipmentReceiptRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentReceiptRole getAddedShipmentReceiptRole() {
		return addedShipmentReceiptRole;
	}

}
