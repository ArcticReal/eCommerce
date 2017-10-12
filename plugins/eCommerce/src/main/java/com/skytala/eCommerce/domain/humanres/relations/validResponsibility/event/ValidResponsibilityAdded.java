package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;
public class ValidResponsibilityAdded implements Event{

	private ValidResponsibility addedValidResponsibility;
	private boolean success;

	public ValidResponsibilityAdded(ValidResponsibility addedValidResponsibility, boolean success){
		this.addedValidResponsibility = addedValidResponsibility;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ValidResponsibility getAddedValidResponsibility() {
		return addedValidResponsibility;
	}

}
