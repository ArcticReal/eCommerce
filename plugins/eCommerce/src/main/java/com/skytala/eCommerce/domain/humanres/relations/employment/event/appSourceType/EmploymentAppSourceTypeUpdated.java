package com.skytala.eCommerce.domain.humanres.relations.employment.event.appSourceType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employment.model.appSourceType.EmploymentAppSourceType;
public class EmploymentAppSourceTypeUpdated implements Event{

	private boolean success;

	public EmploymentAppSourceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
