package com.skytala.eCommerce.domain.picklist.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.picklist.model.Picklist;
public class PicklistDeleted implements Event{

	private boolean success;

	public PicklistDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
