package com.skytala.eCommerce.domain.picklistBin.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.picklistBin.model.PicklistBin;
public class PicklistBinDeleted implements Event{

	private boolean success;

	public PicklistBinDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
