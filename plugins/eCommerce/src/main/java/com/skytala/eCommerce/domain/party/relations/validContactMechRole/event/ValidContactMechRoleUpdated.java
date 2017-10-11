package com.skytala.eCommerce.domain.party.relations.validContactMechRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.validContactMechRole.model.ValidContactMechRole;
public class ValidContactMechRoleUpdated implements Event{

	private boolean success;

	public ValidContactMechRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
