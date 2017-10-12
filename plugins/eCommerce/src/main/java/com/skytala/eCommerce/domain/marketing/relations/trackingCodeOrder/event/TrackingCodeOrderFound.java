package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model.TrackingCodeOrder;
public class TrackingCodeOrderFound implements Event{

	private List<TrackingCodeOrder> trackingCodeOrders;

	public TrackingCodeOrderFound(List<TrackingCodeOrder> trackingCodeOrders) {
		this.trackingCodeOrders = trackingCodeOrders;
	}

	public List<TrackingCodeOrder> getTrackingCodeOrders()	{
		return trackingCodeOrders;
	}

}
