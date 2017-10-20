package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox.WorkEffortTransBox;
public class WorkEffortTransBoxFound implements Event{

	private List<WorkEffortTransBox> workEffortTransBoxs;

	public WorkEffortTransBoxFound(List<WorkEffortTransBox> workEffortTransBoxs) {
		this.workEffortTransBoxs = workEffortTransBoxs;
	}

	public List<WorkEffortTransBox> getWorkEffortTransBoxs()	{
		return workEffortTransBoxs;
	}

}
