package com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.type.DeliverableType;
public class DeliverableTypeUpdated implements Event{

	private boolean success;

	public DeliverableTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
