package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.type.TrackingCodeType;
public class TrackingCodeTypeAdded implements Event{

	private TrackingCodeType addedTrackingCodeType;
	private boolean success;

	public TrackingCodeTypeAdded(TrackingCodeType addedTrackingCodeType, boolean success){
		this.addedTrackingCodeType = addedTrackingCodeType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TrackingCodeType getAddedTrackingCodeType() {
		return addedTrackingCodeType;
	}

}
