package com.skytala.eCommerce.domain.humanres.relations.employment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;
public class EmploymentDeleted implements Event{

	private boolean success;

	public EmploymentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
