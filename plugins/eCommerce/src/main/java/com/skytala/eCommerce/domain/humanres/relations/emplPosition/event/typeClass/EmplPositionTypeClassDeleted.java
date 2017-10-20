package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeClass.EmplPositionTypeClass;
public class EmplPositionTypeClassDeleted implements Event{

	private boolean success;

	public EmplPositionTypeClassDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
