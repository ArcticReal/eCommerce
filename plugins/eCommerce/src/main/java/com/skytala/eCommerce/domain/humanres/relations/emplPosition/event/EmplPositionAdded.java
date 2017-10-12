package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.EmplPosition;
public class EmplPositionAdded implements Event{

	private EmplPosition addedEmplPosition;
	private boolean success;

	public EmplPositionAdded(EmplPosition addedEmplPosition, boolean success){
		this.addedEmplPosition = addedEmplPosition;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplPosition getAddedEmplPosition() {
		return addedEmplPosition;
	}

}
