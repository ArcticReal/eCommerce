package com.skytala.eCommerce.domain.order.relations.custRequest.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.workEffort.CustRequestWorkEffort;
public class CustRequestWorkEffortUpdated implements Event{

	private boolean success;

	public CustRequestWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
