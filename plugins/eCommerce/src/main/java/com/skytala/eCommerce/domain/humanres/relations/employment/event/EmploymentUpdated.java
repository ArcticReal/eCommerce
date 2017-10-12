package com.skytala.eCommerce.domain.humanres.relations.employment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;
public class EmploymentUpdated implements Event{

	private boolean success;

	public EmploymentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
