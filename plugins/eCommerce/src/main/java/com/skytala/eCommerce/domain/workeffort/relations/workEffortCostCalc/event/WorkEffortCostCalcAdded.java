package com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.model.WorkEffortCostCalc;
public class WorkEffortCostCalcAdded implements Event{

	private WorkEffortCostCalc addedWorkEffortCostCalc;
	private boolean success;

	public WorkEffortCostCalcAdded(WorkEffortCostCalc addedWorkEffortCostCalc, boolean success){
		this.addedWorkEffortCostCalc = addedWorkEffortCostCalc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortCostCalc getAddedWorkEffortCostCalc() {
		return addedWorkEffortCostCalc;
	}

}
