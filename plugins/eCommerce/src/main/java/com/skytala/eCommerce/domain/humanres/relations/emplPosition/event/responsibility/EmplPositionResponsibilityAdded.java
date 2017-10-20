package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.responsibility.EmplPositionResponsibility;
public class EmplPositionResponsibilityAdded implements Event{

	private EmplPositionResponsibility addedEmplPositionResponsibility;
	private boolean success;

	public EmplPositionResponsibilityAdded(EmplPositionResponsibility addedEmplPositionResponsibility, boolean success){
		this.addedEmplPositionResponsibility = addedEmplPositionResponsibility;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplPositionResponsibility getAddedEmplPositionResponsibility() {
		return addedEmplPositionResponsibility;
	}

}
