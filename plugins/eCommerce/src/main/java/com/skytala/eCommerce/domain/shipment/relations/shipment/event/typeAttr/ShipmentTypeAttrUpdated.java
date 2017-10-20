package com.skytala.eCommerce.domain.shipment.relations.shipment.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.typeAttr.ShipmentTypeAttr;
public class ShipmentTypeAttrUpdated implements Event{

	private boolean success;

	public ShipmentTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
