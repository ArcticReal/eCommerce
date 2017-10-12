package com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.model.ResponsibilityType;
public class ResponsibilityTypeDeleted implements Event{

	private boolean success;

	public ResponsibilityTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
