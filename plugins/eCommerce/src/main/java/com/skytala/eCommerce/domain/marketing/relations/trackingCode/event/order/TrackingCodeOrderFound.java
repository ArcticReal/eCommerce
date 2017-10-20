package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.order;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.order.TrackingCodeOrder;
public class TrackingCodeOrderFound implements Event{

	private List<TrackingCodeOrder> trackingCodeOrders;

	public TrackingCodeOrderFound(List<TrackingCodeOrder> trackingCodeOrders) {
		this.trackingCodeOrders = trackingCodeOrders;
	}

	public List<TrackingCodeOrder> getTrackingCodeOrders()	{
		return trackingCodeOrders;
	}

}
