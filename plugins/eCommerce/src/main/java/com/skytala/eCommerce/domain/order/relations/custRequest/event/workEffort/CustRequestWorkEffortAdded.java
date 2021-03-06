package com.skytala.eCommerce.domain.order.relations.custRequest.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.workEffort.CustRequestWorkEffort;
public class CustRequestWorkEffortAdded implements Event{

	private CustRequestWorkEffort addedCustRequestWorkEffort;
	private boolean success;

	public CustRequestWorkEffortAdded(CustRequestWorkEffort addedCustRequestWorkEffort, boolean success){
		this.addedCustRequestWorkEffort = addedCustRequestWorkEffort;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestWorkEffort getAddedCustRequestWorkEffort() {
		return addedCustRequestWorkEffort;
	}

}
