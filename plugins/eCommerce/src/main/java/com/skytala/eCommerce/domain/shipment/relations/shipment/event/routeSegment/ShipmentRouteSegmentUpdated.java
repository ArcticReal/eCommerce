package com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.routeSegment.ShipmentRouteSegment;
public class ShipmentRouteSegmentUpdated implements Event{

	private boolean success;

	public ShipmentRouteSegmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
