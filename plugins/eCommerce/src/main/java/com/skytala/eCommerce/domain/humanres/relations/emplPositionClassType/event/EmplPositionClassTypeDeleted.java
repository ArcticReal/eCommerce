package com.skytala.eCommerce.domain.humanres.relations.emplPositionClassType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionClassType.model.EmplPositionClassType;
public class EmplPositionClassTypeDeleted implements Event{

	private boolean success;

	public EmplPositionClassTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
