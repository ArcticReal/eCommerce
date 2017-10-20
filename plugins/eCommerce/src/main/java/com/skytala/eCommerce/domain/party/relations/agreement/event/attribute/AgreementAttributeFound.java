package com.skytala.eCommerce.domain.party.relations.agreement.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.attribute.AgreementAttribute;
public class AgreementAttributeFound implements Event{

	private List<AgreementAttribute> agreementAttributes;

	public AgreementAttributeFound(List<AgreementAttribute> agreementAttributes) {
		this.agreementAttributes = agreementAttributes;
	}

	public List<AgreementAttribute> getAgreementAttributes()	{
		return agreementAttributes;
	}

}
