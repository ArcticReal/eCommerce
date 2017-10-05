package com.skytala.eCommerce.domain.trackingCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.trackingCode.model.TrackingCode;
public class TrackingCodeAdded implements Event{

	private TrackingCode addedTrackingCode;
	private boolean success;

	public TrackingCodeAdded(TrackingCode addedTrackingCode, boolean success){
		this.addedTrackingCode = addedTrackingCode;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TrackingCode getAddedTrackingCode() {
		return addedTrackingCode;
	}

}
