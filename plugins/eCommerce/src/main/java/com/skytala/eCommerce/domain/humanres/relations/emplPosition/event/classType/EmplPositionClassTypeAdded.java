package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.classType.EmplPositionClassType;
public class EmplPositionClassTypeAdded implements Event{

	private EmplPositionClassType addedEmplPositionClassType;
	private boolean success;

	public EmplPositionClassTypeAdded(EmplPositionClassType addedEmplPositionClassType, boolean success){
		this.addedEmplPositionClassType = addedEmplPositionClassType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplPositionClassType getAddedEmplPositionClassType() {
		return addedEmplPositionClassType;
	}

}
