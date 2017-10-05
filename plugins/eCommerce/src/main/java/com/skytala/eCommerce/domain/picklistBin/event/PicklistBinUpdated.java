package com.skytala.eCommerce.domain.picklistBin.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.picklistBin.model.PicklistBin;
public class PicklistBinUpdated implements Event{

	private boolean success;

	public PicklistBinUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
