package com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.model.WorkEffortDeliverableProd;
public class WorkEffortDeliverableProdDeleted implements Event{

	private boolean success;

	public WorkEffortDeliverableProdDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
