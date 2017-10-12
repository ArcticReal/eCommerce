package com.skytala.eCommerce.domain.humanres.relations.employmentApp.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employmentApp.model.EmploymentApp;
public class EmploymentAppDeleted implements Event{

	private boolean success;

	public EmploymentAppDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
