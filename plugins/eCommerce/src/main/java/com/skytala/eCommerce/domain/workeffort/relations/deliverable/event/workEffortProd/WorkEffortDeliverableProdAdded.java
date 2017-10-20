package com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.workEffortProd.WorkEffortDeliverableProd;
public class WorkEffortDeliverableProdAdded implements Event{

	private WorkEffortDeliverableProd addedWorkEffortDeliverableProd;
	private boolean success;

	public WorkEffortDeliverableProdAdded(WorkEffortDeliverableProd addedWorkEffortDeliverableProd, boolean success){
		this.addedWorkEffortDeliverableProd = addedWorkEffortDeliverableProd;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortDeliverableProd getAddedWorkEffortDeliverableProd() {
		return addedWorkEffortDeliverableProd;
	}

}
