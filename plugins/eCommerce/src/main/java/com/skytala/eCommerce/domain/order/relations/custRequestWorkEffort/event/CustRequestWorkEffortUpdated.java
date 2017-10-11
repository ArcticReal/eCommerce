package com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.model.CustRequestWorkEffort;
public class CustRequestWorkEffortUpdated implements Event{

	private boolean success;

	public CustRequestWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
