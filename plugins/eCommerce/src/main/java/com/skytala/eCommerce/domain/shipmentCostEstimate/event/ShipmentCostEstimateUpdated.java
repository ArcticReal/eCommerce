package com.skytala.eCommerce.domain.shipmentCostEstimate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentCostEstimate.model.ShipmentCostEstimate;
public class ShipmentCostEstimateUpdated implements Event{

	private boolean success;

	public ShipmentCostEstimateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
