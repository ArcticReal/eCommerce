package com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.type.DeliverableType;
public class DeliverableTypeDeleted implements Event{

	private boolean success;

	public DeliverableTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
