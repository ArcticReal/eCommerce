package com.skytala.eCommerce.domain.party.relations.agreementTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementTerm.model.AgreementTerm;
public class AgreementTermUpdated implements Event{

	private boolean success;

	public AgreementTermUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
