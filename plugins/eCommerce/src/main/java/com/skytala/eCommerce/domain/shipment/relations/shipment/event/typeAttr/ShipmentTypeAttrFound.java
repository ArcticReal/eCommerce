package com.skytala.eCommerce.domain.shipment.relations.shipment.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.typeAttr.ShipmentTypeAttr;
public class ShipmentTypeAttrFound implements Event{

	private List<ShipmentTypeAttr> shipmentTypeAttrs;

	public ShipmentTypeAttrFound(List<ShipmentTypeAttr> shipmentTypeAttrs) {
		this.shipmentTypeAttrs = shipmentTypeAttrs;
	}

	public List<ShipmentTypeAttr> getShipmentTypeAttrs()	{
		return shipmentTypeAttrs;
	}

}
