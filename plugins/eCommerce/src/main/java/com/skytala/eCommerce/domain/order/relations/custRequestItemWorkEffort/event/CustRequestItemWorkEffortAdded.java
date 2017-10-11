package com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.model.CustRequestItemWorkEffort;
public class CustRequestItemWorkEffortAdded implements Event{

	private CustRequestItemWorkEffort addedCustRequestItemWorkEffort;
	private boolean success;

	public CustRequestItemWorkEffortAdded(CustRequestItemWorkEffort addedCustRequestItemWorkEffort, boolean success){
		this.addedCustRequestItemWorkEffort = addedCustRequestItemWorkEffort;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestItemWorkEffort getAddedCustRequestItemWorkEffort() {
		return addedCustRequestItemWorkEffort;
	}

}
