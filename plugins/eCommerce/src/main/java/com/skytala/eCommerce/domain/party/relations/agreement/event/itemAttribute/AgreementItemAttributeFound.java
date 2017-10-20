package com.skytala.eCommerce.domain.party.relations.agreement.event.itemAttribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.itemAttribute.AgreementItemAttribute;
public class AgreementItemAttributeFound implements Event{

	private List<AgreementItemAttribute> agreementItemAttributes;

	public AgreementItemAttributeFound(List<AgreementItemAttribute> agreementItemAttributes) {
		this.agreementItemAttributes = agreementItemAttributes;
	}

	public List<AgreementItemAttribute> getAgreementItemAttributes()	{
		return agreementItemAttributes;
	}

}
