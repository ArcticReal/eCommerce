package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.visit.TrackingCodeVisit;
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
