package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.type.TrackingCodeType;
public class TrackingCodeTypeUpdated implements Event{

	private boolean success;

	public TrackingCodeTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
