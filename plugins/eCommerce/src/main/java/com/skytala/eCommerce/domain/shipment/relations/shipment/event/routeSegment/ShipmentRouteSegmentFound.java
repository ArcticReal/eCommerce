package com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.routeSegment.ShipmentRouteSegment;
public class ShipmentRouteSegmentFound implements Event{

	private List<ShipmentRouteSegment> shipmentRouteSegments;

	public ShipmentRouteSegmentFound(List<ShipmentRouteSegment> shipmentRouteSegments) {
		this.shipmentRouteSegments = shipmentRouteSegments;
	}

	public List<ShipmentRouteSegment> getShipmentRouteSegments()	{
		return shipmentRouteSegments;
	}

}
