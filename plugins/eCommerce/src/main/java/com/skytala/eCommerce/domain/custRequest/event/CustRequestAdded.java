package com.skytala.eCommerce.domain.custRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequest.model.CustRequest;
public class CustRequestAdded implements Event{

	private CustRequest addedCustRequest;
	private boolean success;

	public CustRequestAdded(CustRequest addedCustRequest, boolean success){
		this.addedCustRequest = addedCustRequest;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequest getAddedCustRequest() {
		return addedCustRequest;
	}

}
