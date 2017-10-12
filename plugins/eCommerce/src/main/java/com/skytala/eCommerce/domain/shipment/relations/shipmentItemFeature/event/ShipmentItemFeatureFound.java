package com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.model.ShipmentItemFeature;
public class ShipmentItemFeatureFound implements Event{

	private List<ShipmentItemFeature> shipmentItemFeatures;

	public ShipmentItemFeatureFound(List<ShipmentItemFeature> shipmentItemFeatures) {
		this.shipmentItemFeatures = shipmentItemFeatures;
	}

	public List<ShipmentItemFeature> getShipmentItemFeatures()	{
		return shipmentItemFeatures;
	}

}
