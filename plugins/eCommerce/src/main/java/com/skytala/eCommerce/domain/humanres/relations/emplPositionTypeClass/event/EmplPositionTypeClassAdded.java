package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.model.EmplPositionTypeClass;
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
