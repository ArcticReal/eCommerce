package com.skytala.eCommerce.domain.deliverableType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deliverableType.model.DeliverableType;
public class DeliverableTypeUpdated implements Event{

	private boolean success;

	public DeliverableTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
