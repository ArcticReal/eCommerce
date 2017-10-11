package com.skytala.eCommerce.domain.order.relations.custRequestStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestStatus.model.CustRequestStatus;
public class CustRequestStatusAdded implements Event{

	private CustRequestStatus addedCustRequestStatus;
	private boolean success;

	public CustRequestStatusAdded(CustRequestStatus addedCustRequestStatus, boolean success){
		this.addedCustRequestStatus = addedCustRequestStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestStatus getAddedCustRequestStatus() {
		return addedCustRequestStatus;
	}

}
