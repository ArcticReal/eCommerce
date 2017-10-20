package com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.routeSegment.ShipmentRouteSegment;
public class ShipmentRouteSegmentAdded implements Event{

	private ShipmentRouteSegment addedShipmentRouteSegment;
	private boolean success;

	public ShipmentRouteSegmentAdded(ShipmentRouteSegment addedShipmentRouteSegment, boolean success){
		this.addedShipmentRouteSegment = addedShipmentRouteSegment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentRouteSegment getAddedShipmentRouteSegment() {
		return addedShipmentRouteSegment;
	}

}
