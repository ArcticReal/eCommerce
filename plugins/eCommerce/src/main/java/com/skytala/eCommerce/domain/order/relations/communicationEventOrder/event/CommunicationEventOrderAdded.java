package com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model.CommunicationEventOrder;
public class CommunicationEventOrderAdded implements Event{

	private CommunicationEventOrder addedCommunicationEventOrder;
	private boolean success;

	public CommunicationEventOrderAdded(CommunicationEventOrder addedCommunicationEventOrder, boolean success){
		this.addedCommunicationEventOrder = addedCommunicationEventOrder;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommunicationEventOrder getAddedCommunicationEventOrder() {
		return addedCommunicationEventOrder;
	}

}
