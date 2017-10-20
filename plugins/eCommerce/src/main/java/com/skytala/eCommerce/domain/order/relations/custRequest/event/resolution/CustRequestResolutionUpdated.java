package com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.resolution.CustRequestResolution;
public class CustRequestResolutionUpdated implements Event{

	private boolean success;

	public CustRequestResolutionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
