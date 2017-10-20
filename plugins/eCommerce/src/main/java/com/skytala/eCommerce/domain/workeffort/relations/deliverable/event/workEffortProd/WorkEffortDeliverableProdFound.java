package com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.workEffortProd.WorkEffortDeliverableProd;
public class WorkEffortDeliverableProdFound implements Event{

	private List<WorkEffortDeliverableProd> workEffortDeliverableProds;

	public WorkEffortDeliverableProdFound(List<WorkEffortDeliverableProd> workEffortDeliverableProds) {
		this.workEffortDeliverableProds = workEffortDeliverableProds;
	}

	public List<WorkEffortDeliverableProd> getWorkEffortDeliverableProds()	{
		return workEffortDeliverableProds;
	}

}
