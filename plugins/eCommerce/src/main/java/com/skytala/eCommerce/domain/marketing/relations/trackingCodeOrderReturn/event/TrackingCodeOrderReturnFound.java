package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.model.TrackingCodeOrderReturn;
public class TrackingCodeOrderReturnFound implements Event{

	private List<TrackingCodeOrderReturn> trackingCodeOrderReturns;

	public TrackingCodeOrderReturnFound(List<TrackingCodeOrderReturn> trackingCodeOrderReturns) {
		this.trackingCodeOrderReturns = trackingCodeOrderReturns;
	}

	public List<TrackingCodeOrderReturn> getTrackingCodeOrderReturns()	{
		return trackingCodeOrderReturns;
	}

}
