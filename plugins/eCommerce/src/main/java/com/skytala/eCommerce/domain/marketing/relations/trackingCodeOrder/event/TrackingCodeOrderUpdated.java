package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model.TrackingCodeOrder;
public class TrackingCodeOrderUpdated implements Event{

	private boolean success;

	public TrackingCodeOrderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
