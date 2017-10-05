package com.skytala.eCommerce.domain.emplPositionClassType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.emplPositionClassType.model.EmplPositionClassType;
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
