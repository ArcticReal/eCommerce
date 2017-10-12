package com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.model.ShipmentCostEstimate;
public class ShipmentCostEstimateFound implements Event{

	private List<ShipmentCostEstimate> shipmentCostEstimates;

	public ShipmentCostEstimateFound(List<ShipmentCostEstimate> shipmentCostEstimates) {
		this.shipmentCostEstimates = shipmentCostEstimates;
	}

	public List<ShipmentCostEstimate> getShipmentCostEstimates()	{
		return shipmentCostEstimates;
	}

}
