package com.skytala.eCommerce.domain.custRequestType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequestType.model.CustRequestType;
public class CustRequestTypeAdded implements Event{

	private CustRequestType addedCustRequestType;
	private boolean success;

	public CustRequestTypeAdded(CustRequestType addedCustRequestType, boolean success){
		this.addedCustRequestType = addedCustRequestType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestType getAddedCustRequestType() {
		return addedCustRequestType;
	}

}
