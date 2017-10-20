package com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.validRole.ValidContactMechRole;
public class ValidContactMechRoleDeleted implements Event{

	private boolean success;

	public ValidContactMechRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
