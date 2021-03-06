package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.type.EmplPositionType;
public class EmplPositionTypeAdded implements Event{

	private EmplPositionType addedEmplPositionType;
	private boolean success;

	public EmplPositionTypeAdded(EmplPositionType addedEmplPositionType, boolean success){
		this.addedEmplPositionType = addedEmplPositionType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplPositionType getAddedEmplPositionType() {
		return addedEmplPositionType;
	}

}
