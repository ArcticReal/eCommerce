package com.skytala.eCommerce.domain.order.relations.custRequest.event.itemWorkEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemWorkEffort.CustRequestItemWorkEffort;
public class CustRequestItemWorkEffortUpdated implements Event{

	private boolean success;

	public CustRequestItemWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
