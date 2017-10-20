package com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.resolution.CustRequestResolution;
public class CustRequestResolutionDeleted implements Event{

	private boolean success;

	public CustRequestResolutionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
