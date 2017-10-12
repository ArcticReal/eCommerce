package com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.model.TrackingCodeType;
public class TrackingCodeTypeDeleted implements Event{

	private boolean success;

	public TrackingCodeTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
