package com.skytala.eCommerce.domain.humanres.relations.employment.event.appSourceType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employment.model.appSourceType.EmploymentAppSourceType;
public class EmploymentAppSourceTypeAdded implements Event{

	private EmploymentAppSourceType addedEmploymentAppSourceType;
	private boolean success;

	public EmploymentAppSourceTypeAdded(EmploymentAppSourceType addedEmploymentAppSourceType, boolean success){
		this.addedEmploymentAppSourceType = addedEmploymentAppSourceType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmploymentAppSourceType getAddedEmploymentAppSourceType() {
		return addedEmploymentAppSourceType;
	}

}
