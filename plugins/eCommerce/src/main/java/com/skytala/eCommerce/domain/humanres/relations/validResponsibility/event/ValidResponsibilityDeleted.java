package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;
public class ValidResponsibilityDeleted implements Event{

	private boolean success;

	public ValidResponsibilityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
