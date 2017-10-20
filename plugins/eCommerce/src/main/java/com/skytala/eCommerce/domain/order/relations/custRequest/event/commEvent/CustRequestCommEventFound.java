package com.skytala.eCommerce.domain.order.relations.custRequest.event.commEvent;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.commEvent.CustRequestCommEvent;
public class CustRequestCommEventFound implements Event{

	private List<CustRequestCommEvent> custRequestCommEvents;

	public CustRequestCommEventFound(List<CustRequestCommEvent> custRequestCommEvents) {
		this.custRequestCommEvents = custRequestCommEvents;
	}

	public List<CustRequestCommEvent> getCustRequestCommEvents()	{
		return custRequestCommEvents;
	}

}
