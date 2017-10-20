package com.skytala.eCommerce.domain.party.relations.agreement.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.type.AgreementType;
public class AgreementTypeFound implements Event{

	private List<AgreementType> agreementTypes;

	public AgreementTypeFound(List<AgreementType> agreementTypes) {
		this.agreementTypes = agreementTypes;
	}

	public List<AgreementType> getAgreementTypes()	{
		return agreementTypes;
	}

}
