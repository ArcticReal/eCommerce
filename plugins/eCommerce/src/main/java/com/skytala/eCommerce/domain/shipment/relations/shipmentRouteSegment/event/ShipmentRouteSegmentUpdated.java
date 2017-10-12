package com.skytala.eCommerce.domain.shipment.relations.shipmentRouteSegment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentRouteSegment.model.ShipmentRouteSegment;
public class ShipmentRouteSegmentUpdated implements Event{

	private boolean success;

	public ShipmentRouteSegmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
