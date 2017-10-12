package com.skytala.eCommerce.domain.humanres.relations.emplPositionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.model.EmplPositionType;
public class EmplPositionTypeUpdated implements Event{

	private boolean success;

	public EmplPositionTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
