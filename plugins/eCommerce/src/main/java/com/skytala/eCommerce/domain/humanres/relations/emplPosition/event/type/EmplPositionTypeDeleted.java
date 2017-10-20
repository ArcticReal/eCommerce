package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.type.EmplPositionType;
public class EmplPositionTypeDeleted implements Event{

	private boolean success;

	public EmplPositionTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
