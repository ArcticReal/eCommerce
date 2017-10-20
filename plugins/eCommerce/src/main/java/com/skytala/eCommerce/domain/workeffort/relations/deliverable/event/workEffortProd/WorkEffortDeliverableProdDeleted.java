package com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.workEffortProd.WorkEffortDeliverableProd;
public class WorkEffortDeliverableProdDeleted implements Event{

	private boolean success;

	public WorkEffortDeliverableProdDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
