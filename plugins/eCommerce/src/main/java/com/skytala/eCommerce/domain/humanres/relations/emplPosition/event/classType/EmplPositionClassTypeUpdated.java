package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.classType.EmplPositionClassType;
public class EmplPositionClassTypeUpdated implements Event{

	private boolean success;

	public EmplPositionClassTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
