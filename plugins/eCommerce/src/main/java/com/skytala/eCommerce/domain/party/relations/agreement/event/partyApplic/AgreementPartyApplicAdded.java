package com.skytala.eCommerce.domain.party.relations.agreement.event.partyApplic;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.partyApplic.AgreementPartyApplic;
public class AgreementPartyApplicAdded implements Event{

	private AgreementPartyApplic addedAgreementPartyApplic;
	private boolean success;

	public AgreementPartyApplicAdded(AgreementPartyApplic addedAgreementPartyApplic, boolean success){
		this.addedAgreementPartyApplic = addedAgreementPartyApplic;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementPartyApplic getAddedAgreementPartyApplic() {
		return addedAgreementPartyApplic;
	}

}
