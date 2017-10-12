package com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.model.WorkEffortBilling;
public class WorkEffortBillingFound implements Event{

	private List<WorkEffortBilling> workEffortBillings;

	public WorkEffortBillingFound(List<WorkEffortBilling> workEffortBillings) {
		this.workEffortBillings = workEffortBillings;
	}

	public List<WorkEffortBilling> getWorkEffortBillings()	{
		return workEffortBillings;
	}

}
