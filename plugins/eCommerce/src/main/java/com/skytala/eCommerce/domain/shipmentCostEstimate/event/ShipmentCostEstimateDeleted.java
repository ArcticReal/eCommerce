package com.skytala.eCommerce.domain.shipmentCostEstimate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentCostEstimate.model.ShipmentCostEstimate;
public class ShipmentCostEstimateDeleted implements Event{

	private boolean success;

	public ShipmentCostEstimateDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
