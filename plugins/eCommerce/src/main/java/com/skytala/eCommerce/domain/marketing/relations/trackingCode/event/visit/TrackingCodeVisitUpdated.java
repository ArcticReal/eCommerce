package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.visit.TrackingCodeVisit;
public class TrackingCodeVisitUpdated implements Event{

	private boolean success;

	public TrackingCodeVisitUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
