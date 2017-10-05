package com.skytala.eCommerce.domain.custRequestResolution.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequestResolution.model.CustRequestResolution;
public class CustRequestResolutionUpdated implements Event{

	private boolean success;

	public CustRequestResolutionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
