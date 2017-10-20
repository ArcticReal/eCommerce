package com.skytala.eCommerce.domain.party.relations.agreement.event.term;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.term.AgreementTerm;
public class AgreementTermAdded implements Event{

	private AgreementTerm addedAgreementTerm;
	private boolean success;

	public AgreementTermAdded(AgreementTerm addedAgreementTerm, boolean success){
		this.addedAgreementTerm = addedAgreementTerm;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementTerm getAddedAgreementTerm() {
		return addedAgreementTerm;
	}

}
