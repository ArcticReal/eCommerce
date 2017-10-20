package com.skytala.eCommerce.domain.party.relations.agreement.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.role.AgreementRole;
public class AgreementRoleUpdated implements Event{

	private boolean success;

	public AgreementRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
