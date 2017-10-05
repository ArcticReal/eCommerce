package com.skytala.eCommerce.domain.custRequestResolution.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequestResolution.model.CustRequestResolution;
public class CustRequestResolutionDeleted implements Event{

	private boolean success;

	public CustRequestResolutionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
