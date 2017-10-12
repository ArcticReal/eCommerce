package com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.model.ShipmentCostEstimate;
public class ShipmentCostEstimateUpdated implements Event{

	private boolean success;

	public ShipmentCostEstimateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
