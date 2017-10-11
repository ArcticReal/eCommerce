package com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.model.CustRequestCommEvent;
public class CustRequestCommEventFound implements Event{

	private List<CustRequestCommEvent> custRequestCommEvents;

	public CustRequestCommEventFound(List<CustRequestCommEvent> custRequestCommEvents) {
		this.custRequestCommEvents = custRequestCommEvents;
	}

	public List<CustRequestCommEvent> getCustRequestCommEvents()	{
		return custRequestCommEvents;
	}

}
