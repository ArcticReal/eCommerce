package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeClass.EmplPositionTypeClass;
public class EmplPositionTypeClassUpdated implements Event{

	private boolean success;

	public EmplPositionTypeClassUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
