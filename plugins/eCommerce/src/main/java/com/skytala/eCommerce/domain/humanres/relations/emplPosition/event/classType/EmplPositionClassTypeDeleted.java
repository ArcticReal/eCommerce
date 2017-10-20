package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.classType.EmplPositionClassType;
public class EmplPositionClassTypeDeleted implements Event{

	private boolean success;

	public EmplPositionClassTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
