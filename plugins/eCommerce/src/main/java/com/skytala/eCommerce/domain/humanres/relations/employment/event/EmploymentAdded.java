package com.skytala.eCommerce.domain.humanres.relations.employment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;
public class EmploymentAdded implements Event{

	private Employment addedEmployment;
	private boolean success;

	public EmploymentAdded(Employment addedEmployment, boolean success){
		this.addedEmployment = addedEmployment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Employment getAddedEmployment() {
		return addedEmployment;
	}

}
