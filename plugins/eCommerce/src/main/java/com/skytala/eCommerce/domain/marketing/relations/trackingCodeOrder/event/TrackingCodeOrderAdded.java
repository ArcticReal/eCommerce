package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model.TrackingCodeOrder;
public class TrackingCodeOrderAdded implements Event{

	private TrackingCodeOrder addedTrackingCodeOrder;
	private boolean success;

	public TrackingCodeOrderAdded(TrackingCodeOrder addedTrackingCodeOrder, boolean success){
		this.addedTrackingCodeOrder = addedTrackingCodeOrder;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TrackingCodeOrder getAddedTrackingCodeOrder() {
		return addedTrackingCodeOrder;
	}

}
