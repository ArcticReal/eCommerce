package com.skytala.eCommerce.domain.party.relations.agreement.event.partyApplic;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.partyApplic.AgreementPartyApplic;
public class AgreementPartyApplicDeleted implements Event{

	private boolean success;

	public AgreementPartyApplicDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
