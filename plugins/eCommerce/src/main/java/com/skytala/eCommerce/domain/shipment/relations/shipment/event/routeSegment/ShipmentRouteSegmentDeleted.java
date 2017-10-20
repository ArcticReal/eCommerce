package com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.routeSegment.ShipmentRouteSegment;
public class ShipmentRouteSegmentDeleted implements Event{

	private boolean success;

	public ShipmentRouteSegmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
