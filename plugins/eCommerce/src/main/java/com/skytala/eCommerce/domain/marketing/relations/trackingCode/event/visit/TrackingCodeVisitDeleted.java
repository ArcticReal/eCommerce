package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.visit.TrackingCodeVisit;
public class TrackingCodeVisitDeleted implements Event{

	private boolean success;

	public TrackingCodeVisitDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
