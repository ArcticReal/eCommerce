package com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.model.TrackingCodeVisit;
public class TrackingCodeVisitUpdated implements Event{

	private boolean success;

	public TrackingCodeVisitUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
