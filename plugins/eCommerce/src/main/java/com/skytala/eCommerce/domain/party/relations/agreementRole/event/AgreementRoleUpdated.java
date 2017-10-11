package com.skytala.eCommerce.domain.party.relations.agreementRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementRole.model.AgreementRole;
public class AgreementRoleUpdated implements Event{

	private boolean success;

	public AgreementRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
