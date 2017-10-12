package com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.model.WorkEffortDeliverableProd;
public class WorkEffortDeliverableProdUpdated implements Event{

	private boolean success;

	public WorkEffortDeliverableProdUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
