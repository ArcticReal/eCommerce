package com.skytala.eCommerce.domain.party.relations.agreement.event.partyApplic;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.partyApplic.AgreementPartyApplic;
public class AgreementPartyApplicUpdated implements Event{

	private boolean success;

	public AgreementPartyApplicUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
