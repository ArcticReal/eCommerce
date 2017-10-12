package com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.model.ShipmentTypeAttr;
public class ShipmentTypeAttrFound implements Event{

	private List<ShipmentTypeAttr> shipmentTypeAttrs;

	public ShipmentTypeAttrFound(List<ShipmentTypeAttr> shipmentTypeAttrs) {
		this.shipmentTypeAttrs = shipmentTypeAttrs;
	}

	public List<ShipmentTypeAttr> getShipmentTypeAttrs()	{
		return shipmentTypeAttrs;
	}

}
