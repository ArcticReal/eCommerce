package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeClass.EmplPositionTypeClass;
public class EmplPositionTypeClassAdded implements Event{

	private EmplPositionTypeClass addedEmplPositionTypeClass;
	private boolean success;

	public EmplPositionTypeClassAdded(EmplPositionTypeClass addedEmplPositionTypeClass, boolean success){
		this.addedEmplPositionTypeClass = addedEmplPositionTypeClass;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplPositionTypeClass getAddedEmplPositionTypeClass() {
		return addedEmplPositionTypeClass;
	}

}
