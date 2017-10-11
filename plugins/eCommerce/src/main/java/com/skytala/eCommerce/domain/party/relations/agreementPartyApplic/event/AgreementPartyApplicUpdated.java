package com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.model.AgreementPartyApplic;
public class AgreementPartyApplicUpdated implements Event{

	private boolean success;

	public AgreementPartyApplicUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
