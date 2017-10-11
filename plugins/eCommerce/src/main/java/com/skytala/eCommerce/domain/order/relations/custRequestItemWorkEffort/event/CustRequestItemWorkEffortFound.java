package com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.model.CustRequestItemWorkEffort;
public class CustRequestItemWorkEffortFound implements Event{

	private List<CustRequestItemWorkEffort> custRequestItemWorkEfforts;

	public CustRequestItemWorkEffortFound(List<CustRequestItemWorkEffort> custRequestItemWorkEfforts) {
		this.custRequestItemWorkEfforts = custRequestItemWorkEfforts;
	}

	public List<CustRequestItemWorkEffort> getCustRequestItemWorkEfforts()	{
		return custRequestItemWorkEfforts;
	}

}
