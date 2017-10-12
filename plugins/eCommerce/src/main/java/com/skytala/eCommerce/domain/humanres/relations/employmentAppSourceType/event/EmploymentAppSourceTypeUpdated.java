package com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.model.EmploymentAppSourceType;
public class EmploymentAppSourceTypeUpdated implements Event{

	private boolean success;

	public EmploymentAppSourceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
