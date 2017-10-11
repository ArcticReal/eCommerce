package com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model.CommunicationEventOrder;
public class CommunicationEventOrderDeleted implements Event{

	private boolean success;

	public CommunicationEventOrderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
