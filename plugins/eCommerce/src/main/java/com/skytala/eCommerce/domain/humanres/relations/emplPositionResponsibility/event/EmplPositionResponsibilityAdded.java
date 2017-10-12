package com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.model.EmplPositionResponsibility;
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
