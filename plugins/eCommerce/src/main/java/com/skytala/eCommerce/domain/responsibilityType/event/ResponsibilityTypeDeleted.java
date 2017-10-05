package com.skytala.eCommerce.domain.responsibilityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.responsibilityType.model.ResponsibilityType;
public class ResponsibilityTypeDeleted implements Event{

	private boolean success;

	public ResponsibilityTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
