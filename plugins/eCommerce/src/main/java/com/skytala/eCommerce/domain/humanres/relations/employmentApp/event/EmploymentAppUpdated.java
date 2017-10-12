package com.skytala.eCommerce.domain.humanres.relations.employmentApp.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employmentApp.model.EmploymentApp;
public class EmploymentAppUpdated implements Event{

	private boolean success;

	public EmploymentAppUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
