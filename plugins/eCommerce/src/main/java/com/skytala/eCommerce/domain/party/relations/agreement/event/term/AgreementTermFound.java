package com.skytala.eCommerce.domain.party.relations.agreement.event.term;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.term.AgreementTerm;
public class AgreementTermFound implements Event{

	private List<AgreementTerm> agreementTerms;

	public AgreementTermFound(List<AgreementTerm> agreementTerms) {
		this.agreementTerms = agreementTerms;
	}

	public List<AgreementTerm> getAgreementTerms()	{
		return agreementTerms;
	}

}
