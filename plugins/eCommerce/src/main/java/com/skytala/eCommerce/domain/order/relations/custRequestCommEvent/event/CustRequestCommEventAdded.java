package com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.model.CustRequestCommEvent;
public class CustRequestCommEventAdded implements Event{

	private CustRequestCommEvent addedCustRequestCommEvent;
	private boolean success;

	public CustRequestCommEventAdded(CustRequestCommEvent addedCustRequestCommEvent, boolean success){
		this.addedCustRequestCommEvent = addedCustRequestCommEvent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestCommEvent getAddedCustRequestCommEvent() {
		return addedCustRequestCommEvent;
	}

}
