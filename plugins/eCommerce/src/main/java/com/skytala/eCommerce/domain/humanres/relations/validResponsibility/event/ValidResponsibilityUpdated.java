package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;
public class ValidResponsibilityUpdated implements Event{

	private boolean success;

	public ValidResponsibilityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
