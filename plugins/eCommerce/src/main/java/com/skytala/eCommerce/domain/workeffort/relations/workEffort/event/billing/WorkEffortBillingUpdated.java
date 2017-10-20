package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.billing;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.billing.WorkEffortBilling;
public class WorkEffortBillingUpdated implements Event{

	private boolean success;

	public WorkEffortBillingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
