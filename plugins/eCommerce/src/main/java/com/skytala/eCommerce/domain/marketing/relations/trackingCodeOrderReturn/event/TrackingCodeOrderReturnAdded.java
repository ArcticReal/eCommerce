package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.model.TrackingCodeOrderReturn;
public class TrackingCodeOrderReturnAdded implements Event{

	private TrackingCodeOrderReturn addedTrackingCodeOrderReturn;
	private boolean success;

	public TrackingCodeOrderReturnAdded(TrackingCodeOrderReturn addedTrackingCodeOrderReturn, boolean success){
		this.addedTrackingCodeOrderReturn = addedTrackingCodeOrderReturn;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TrackingCodeOrderReturn getAddedTrackingCodeOrderReturn() {
		return addedTrackingCodeOrderReturn;
	}

}
