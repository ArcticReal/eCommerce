package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model.TrackingCodeOrder;
public class TrackingCodeOrderDeleted implements Event{

	private boolean success;

	public TrackingCodeOrderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
