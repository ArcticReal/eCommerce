package com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.model.ShipmentItemFeature;
public class ShipmentItemFeatureUpdated implements Event{

	private boolean success;

	public ShipmentItemFeatureUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
