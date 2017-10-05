package com.skytala.eCommerce.domain.trackingCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.trackingCode.model.TrackingCode;
public class TrackingCodeUpdated implements Event{

	private boolean success;

	public TrackingCodeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
