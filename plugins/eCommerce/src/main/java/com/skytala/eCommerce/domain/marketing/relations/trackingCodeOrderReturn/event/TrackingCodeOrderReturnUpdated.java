package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.model.TrackingCodeOrderReturn;
public class TrackingCodeOrderReturnUpdated implements Event{

	private boolean success;

	public TrackingCodeOrderReturnUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
