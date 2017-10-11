package com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.model.AgreementPartyApplic;
public class AgreementPartyApplicFound implements Event{

	private List<AgreementPartyApplic> agreementPartyApplics;

	public AgreementPartyApplicFound(List<AgreementPartyApplic> agreementPartyApplics) {
		this.agreementPartyApplics = agreementPartyApplics;
	}

	public List<AgreementPartyApplic> getAgreementPartyApplics()	{
		return agreementPartyApplics;
	}

}
