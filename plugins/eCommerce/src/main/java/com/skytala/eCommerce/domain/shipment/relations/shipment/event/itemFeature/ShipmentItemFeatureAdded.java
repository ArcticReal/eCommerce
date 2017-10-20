package com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemFeature;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemFeature.ShipmentItemFeature;
public class ShipmentItemFeatureAdded implements Event{

	private ShipmentItemFeature addedShipmentItemFeature;
	private boolean success;

	public ShipmentItemFeatureAdded(ShipmentItemFeature addedShipmentItemFeature, boolean success){
		this.addedShipmentItemFeature = addedShipmentItemFeature;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentItemFeature getAddedShipmentItemFeature() {
		return addedShipmentItemFeature;
	}

}
