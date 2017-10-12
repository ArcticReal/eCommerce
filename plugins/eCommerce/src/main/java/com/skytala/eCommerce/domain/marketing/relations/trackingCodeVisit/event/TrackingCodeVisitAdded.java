package com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.model.TrackingCodeVisit;
public class TrackingCodeVisitAdded implements Event{

	private TrackingCodeVisit addedTrackingCodeVisit;
	private boolean success;

	public TrackingCodeVisitAdded(TrackingCodeVisit addedTrackingCodeVisit, boolean success){
		this.addedTrackingCodeVisit = addedTrackingCodeVisit;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TrackingCodeVisit getAddedTrackingCodeVisit() {
		return addedTrackingCodeVisit;
	}

}
