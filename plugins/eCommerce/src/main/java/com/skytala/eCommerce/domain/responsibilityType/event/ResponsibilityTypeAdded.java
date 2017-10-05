package com.skytala.eCommerce.domain.responsibilityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.responsibilityType.model.ResponsibilityType;
public class ResponsibilityTypeAdded implements Event{

	private ResponsibilityType addedResponsibilityType;
	private boolean success;

	public ResponsibilityTypeAdded(ResponsibilityType addedResponsibilityType, boolean success){
		this.addedResponsibilityType = addedResponsibilityType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ResponsibilityType getAddedResponsibilityType() {
		return addedResponsibilityType;
	}

}
