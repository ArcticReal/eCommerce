package com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.model.ResponsibilityType;
public class ResponsibilityTypeUpdated implements Event{

	private boolean success;

	public ResponsibilityTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
