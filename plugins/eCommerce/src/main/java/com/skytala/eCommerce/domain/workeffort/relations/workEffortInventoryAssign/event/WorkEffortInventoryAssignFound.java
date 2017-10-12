package com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.model.WorkEffortInventoryAssign;
public class WorkEffortInventoryAssignFound implements Event{

	private List<WorkEffortInventoryAssign> workEffortInventoryAssigns;

	public WorkEffortInventoryAssignFound(List<WorkEffortInventoryAssign> workEffortInventoryAssigns) {
		this.workEffortInventoryAssigns = workEffortInventoryAssigns;
	}

	public List<WorkEffortInventoryAssign> getWorkEffortInventoryAssigns()	{
		return workEffortInventoryAssigns;
	}

}
