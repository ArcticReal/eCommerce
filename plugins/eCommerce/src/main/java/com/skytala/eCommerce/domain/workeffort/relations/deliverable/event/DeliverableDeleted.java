package com.skytala.eCommerce.domain.workeffort.relations.deliverable.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.Deliverable;
public class DeliverableDeleted implements Event{

	private boolean success;

	public DeliverableDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
