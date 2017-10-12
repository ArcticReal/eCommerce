package com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.model.WorkEffortCostCalc;
public class WorkEffortCostCalcUpdated implements Event{

	private boolean success;

	public WorkEffortCostCalcUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
