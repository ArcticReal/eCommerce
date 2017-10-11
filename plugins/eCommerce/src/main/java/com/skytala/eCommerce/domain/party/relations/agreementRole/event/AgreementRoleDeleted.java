package com.skytala.eCommerce.domain.party.relations.agreementRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementRole.model.AgreementRole;
public class AgreementRoleDeleted implements Event{

	private boolean success;

	public AgreementRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
