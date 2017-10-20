package com.skytala.eCommerce.domain.shipment.relations.shipment.event.costEstimate;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.costEstimate.ShipmentCostEstimate;
public class ShipmentCostEstimateFound implements Event{

	private List<ShipmentCostEstimate> shipmentCostEstimates;

	public ShipmentCostEstimateFound(List<ShipmentCostEstimate> shipmentCostEstimates) {
		this.shipmentCostEstimates = shipmentCostEstimates;
	}

	public List<ShipmentCostEstimate> getShipmentCostEstimates()	{
		return shipmentCostEstimates;
	}

}
