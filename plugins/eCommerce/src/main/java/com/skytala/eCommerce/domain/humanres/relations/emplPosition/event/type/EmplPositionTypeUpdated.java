package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.type.EmplPositionType;
public class EmplPositionTypeUpdated implements Event{

	private boolean success;

	public EmplPositionTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
