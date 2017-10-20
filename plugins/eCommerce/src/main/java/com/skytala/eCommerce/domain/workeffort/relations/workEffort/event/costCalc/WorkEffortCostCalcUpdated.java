package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.costCalc.WorkEffortCostCalc;
public class WorkEffortCostCalcUpdated implements Event{

	private boolean success;

	public WorkEffortCostCalcUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
