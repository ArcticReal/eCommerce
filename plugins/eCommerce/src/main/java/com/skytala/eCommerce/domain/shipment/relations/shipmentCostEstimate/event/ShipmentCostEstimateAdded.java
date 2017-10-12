package com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.model.ShipmentCostEstimate;
public class ShipmentCostEstimateAdded implements Event{

	private ShipmentCostEstimate addedShipmentCostEstimate;
	private boolean success;

	public ShipmentCostEstimateAdded(ShipmentCostEstimate addedShipmentCostEstimate, boolean success){
		this.addedShipmentCostEstimate = addedShipmentCostEstimate;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentCostEstimate getAddedShipmentCostEstimate() {
		return addedShipmentCostEstimate;
	}

}
