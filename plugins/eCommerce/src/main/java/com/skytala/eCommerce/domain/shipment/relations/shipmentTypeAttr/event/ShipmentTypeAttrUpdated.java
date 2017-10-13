package com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.model.ShipmentTypeAttr;
public class ShipmentTypeAttrUpdated implements Event{

	private boolean success;

	public ShipmentTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}