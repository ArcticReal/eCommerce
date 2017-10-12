package com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.model.EmploymentAppSourceType;
public class EmploymentAppSourceTypeDeleted implements Event{

	private boolean success;

	public EmploymentAppSourceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
