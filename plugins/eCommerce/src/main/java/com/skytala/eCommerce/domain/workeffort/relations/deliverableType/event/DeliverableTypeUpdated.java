package com.skytala.eCommerce.domain.workeffort.relations.deliverableType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverableType.model.DeliverableType;
public class DeliverableTypeUpdated implements Event{

	private boolean success;

	public DeliverableTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
