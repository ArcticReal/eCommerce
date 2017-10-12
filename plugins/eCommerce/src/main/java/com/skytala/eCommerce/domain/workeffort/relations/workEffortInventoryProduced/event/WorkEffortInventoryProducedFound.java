package com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryProduced.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryProduced.model.WorkEffortInventoryProduced;
public class WorkEffortInventoryProducedFound implements Event{

	private List<WorkEffortInventoryProduced> workEffortInventoryProduceds;

	public WorkEffortInventoryProducedFound(List<WorkEffortInventoryProduced> workEffortInventoryProduceds) {
		this.workEffortInventoryProduceds = workEffortInventoryProduceds;
	}

	public List<WorkEffortInventoryProduced> getWorkEffortInventoryProduceds()	{
		return workEffortInventoryProduceds;
	}

}
