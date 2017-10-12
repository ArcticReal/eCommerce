package com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.model.ShipmentReceiptRole;
public class ShipmentReceiptRoleFound implements Event{

	private List<ShipmentReceiptRole> shipmentReceiptRoles;

	public ShipmentReceiptRoleFound(List<ShipmentReceiptRole> shipmentReceiptRoles) {
		this.shipmentReceiptRoles = shipmentReceiptRoles;
	}

	public List<ShipmentReceiptRole> getShipmentReceiptRoles()	{
		return shipmentReceiptRoles;
	}

}
