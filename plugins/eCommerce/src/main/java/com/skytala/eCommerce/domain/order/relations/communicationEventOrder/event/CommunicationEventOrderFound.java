package com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model.CommunicationEventOrder;
public class CommunicationEventOrderFound implements Event{

	private List<CommunicationEventOrder> communicationEventOrders;

	public CommunicationEventOrderFound(List<CommunicationEventOrder> communicationEventOrders) {
		this.communicationEventOrders = communicationEventOrders;
	}

	public List<CommunicationEventOrder> getCommunicationEventOrders()	{
		return communicationEventOrders;
	}

}
