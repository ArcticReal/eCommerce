package com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.model.WorkEffortCostCalc;
public class WorkEffortCostCalcFound implements Event{

	private List<WorkEffortCostCalc> workEffortCostCalcs;

	public WorkEffortCostCalcFound(List<WorkEffortCostCalc> workEffortCostCalcs) {
		this.workEffortCostCalcs = workEffortCostCalcs;
	}

	public List<WorkEffortCostCalc> getWorkEffortCostCalcs()	{
		return workEffortCostCalcs;
	}

}
