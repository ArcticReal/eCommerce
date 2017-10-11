package com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.model.CustRequestItemWorkEffort;
public class CustRequestItemWorkEffortUpdated implements Event{

	private boolean success;

	public CustRequestItemWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
