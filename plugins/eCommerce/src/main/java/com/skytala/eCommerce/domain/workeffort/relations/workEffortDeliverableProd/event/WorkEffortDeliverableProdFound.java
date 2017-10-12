package com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.model.WorkEffortDeliverableProd;
public class WorkEffortDeliverableProdFound implements Event{

	private List<WorkEffortDeliverableProd> workEffortDeliverableProds;

	public WorkEffortDeliverableProdFound(List<WorkEffortDeliverableProd> workEffortDeliverableProds) {
		this.workEffortDeliverableProds = workEffortDeliverableProds;
	}

	public List<WorkEffortDeliverableProd> getWorkEffortDeliverableProds()	{
		return workEffortDeliverableProds;
	}

}
