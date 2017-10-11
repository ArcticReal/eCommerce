package com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.model.AgreementItemAttribute;
public class AgreementItemAttributeFound implements Event{

	private List<AgreementItemAttribute> agreementItemAttributes;

	public AgreementItemAttributeFound(List<AgreementItemAttribute> agreementItemAttributes) {
		this.agreementItemAttributes = agreementItemAttributes;
	}

	public List<AgreementItemAttribute> getAgreementItemAttributes()	{
		return agreementItemAttributes;
	}

}
