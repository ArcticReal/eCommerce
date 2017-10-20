package com.skytala.eCommerce.domain.shipment.relations.shipment.event.receiptRole;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receiptRole.ShipmentReceiptRole;
public class ShipmentReceiptRoleFound implements Event{

	private List<ShipmentReceiptRole> shipmentReceiptRoles;

	public ShipmentReceiptRoleFound(List<ShipmentReceiptRole> shipmentReceiptRoles) {
		this.shipmentReceiptRoles = shipmentReceiptRoles;
	}

	public List<ShipmentReceiptRole> getShipmentReceiptRoles()	{
		return shipmentReceiptRoles;
	}

}
