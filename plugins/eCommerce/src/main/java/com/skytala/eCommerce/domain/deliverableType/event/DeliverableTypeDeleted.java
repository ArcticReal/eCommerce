package com.skytala.eCommerce.domain.deliverableType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deliverableType.model.DeliverableType;
public class DeliverableTypeDeleted implements Event{

	private boolean success;

	public DeliverableTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
