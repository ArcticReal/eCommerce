package com.skytala.eCommerce.domain.order.relations.custRequest.event.workEffort;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.workEffort.CustRequestWorkEffort;
public class CustRequestWorkEffortFound implements Event{

	private List<CustRequestWorkEffort> custRequestWorkEfforts;

	public CustRequestWorkEffortFound(List<CustRequestWorkEffort> custRequestWorkEfforts) {
		this.custRequestWorkEfforts = custRequestWorkEfforts;
	}

	public List<CustRequestWorkEffort> getCustRequestWorkEfforts()	{
		return custRequestWorkEfforts;
	}

}
