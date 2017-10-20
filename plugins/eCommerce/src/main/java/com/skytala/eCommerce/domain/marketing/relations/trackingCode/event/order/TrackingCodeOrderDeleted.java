package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.order;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.order.TrackingCodeOrder;
public class TrackingCodeOrderDeleted implements Event{

	private boolean success;

	public TrackingCodeOrderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
