package com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.model.WorkEffortCostCalc;
public class WorkEffortCostCalcDeleted implements Event{

	private boolean success;

	public WorkEffortCostCalcDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
