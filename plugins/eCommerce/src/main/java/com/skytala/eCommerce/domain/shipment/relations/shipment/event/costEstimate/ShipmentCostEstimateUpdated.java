package com.skytala.eCommerce.domain.shipment.relations.shipment.event.costEstimate;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.costEstimate.ShipmentCostEstimate;
public class ShipmentCostEstimateUpdated implements Event{

	private boolean success;

	public ShipmentCostEstimateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
