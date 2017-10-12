package com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.model.WorkEffortBilling;
public class WorkEffortBillingDeleted implements Event{

	private boolean success;

	public WorkEffortBillingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
