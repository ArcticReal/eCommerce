package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.TrackingCode;
public class TrackingCodeDeleted implements Event{

	private boolean success;

	public TrackingCodeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
