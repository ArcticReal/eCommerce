package com.skytala.eCommerce.domain.agreementType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreementType.model.AgreementType;
public class AgreementTypeFound implements Event{

	private List<AgreementType> agreementTypes;

	public AgreementTypeFound(List<AgreementType> agreementTypes) {
		this.agreementTypes = agreementTypes;
	}

	public List<AgreementType> getAgreementTypes()	{
		return agreementTypes;
	}

}
