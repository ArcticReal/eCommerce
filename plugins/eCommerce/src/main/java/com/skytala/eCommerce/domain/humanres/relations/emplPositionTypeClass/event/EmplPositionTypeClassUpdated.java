package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.model.EmplPositionTypeClass;
public class EmplPositionTypeClassUpdated implements Event{

	private boolean success;

	public EmplPositionTypeClassUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
