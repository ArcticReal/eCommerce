package com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.model.TrackingCodeVisit;
public class TrackingCodeVisitDeleted implements Event{

	private boolean success;

	public TrackingCodeVisitDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
