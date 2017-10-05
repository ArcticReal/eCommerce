package com.skytala.eCommerce.domain.picklist.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.picklist.model.Picklist;
public class PicklistUpdated implements Event{

	private boolean success;

	public PicklistUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
