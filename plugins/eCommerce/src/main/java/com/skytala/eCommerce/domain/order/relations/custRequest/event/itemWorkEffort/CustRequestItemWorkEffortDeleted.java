package com.skytala.eCommerce.domain.order.relations.custRequest.event.itemWorkEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemWorkEffort.CustRequestItemWorkEffort;
public class CustRequestItemWorkEffortDeleted implements Event{

	private boolean success;

	public CustRequestItemWorkEffortDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
