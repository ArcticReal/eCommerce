package com.skytala.eCommerce.domain.workeffort.relations.deliverableType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverableType.model.DeliverableType;
public class DeliverableTypeDeleted implements Event{

	private boolean success;

	public DeliverableTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
