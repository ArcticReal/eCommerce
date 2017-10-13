package com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.model.WorkEffortBilling;
public class WorkEffortBillingAdded implements Event{

	private WorkEffortBilling addedWorkEffortBilling;
	private boolean success;

	public WorkEffortBillingAdded(WorkEffortBilling addedWorkEffortBilling, boolean success){
		this.addedWorkEffortBilling = addedWorkEffortBilling;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortBilling getAddedWorkEffortBilling() {
		return addedWorkEffortBilling;
	}

}