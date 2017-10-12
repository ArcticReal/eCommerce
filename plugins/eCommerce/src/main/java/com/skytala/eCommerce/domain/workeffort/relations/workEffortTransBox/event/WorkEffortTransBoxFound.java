package com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.model.WorkEffortTransBox;
public class WorkEffortTransBoxFound implements Event{

	private List<WorkEffortTransBox> workEffortTransBoxs;

	public WorkEffortTransBoxFound(List<WorkEffortTransBox> workEffortTransBoxs) {
		this.workEffortTransBoxs = workEffortTransBoxs;
	}

	public List<WorkEffortTransBox> getWorkEffortTransBoxs()	{
		return workEffortTransBoxs;
	}

}
