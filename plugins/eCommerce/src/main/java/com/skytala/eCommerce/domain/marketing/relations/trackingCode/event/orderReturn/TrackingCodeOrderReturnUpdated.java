package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.orderReturn;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.orderReturn.TrackingCodeOrderReturn;
public class TrackingCodeOrderReturnUpdated implements Event{

	private boolean success;

	public TrackingCodeOrderReturnUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
