package com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemFeature;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemFeature.ShipmentItemFeature;
public class ShipmentItemFeatureUpdated implements Event{

	private boolean success;

	public ShipmentItemFeatureUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
