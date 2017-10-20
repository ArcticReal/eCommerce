package com.skytala.eCommerce.domain.shipment.relations.shipment.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.typeAttr.ShipmentTypeAttr;
public class ShipmentTypeAttrDeleted implements Event{

	private boolean success;

	public ShipmentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
