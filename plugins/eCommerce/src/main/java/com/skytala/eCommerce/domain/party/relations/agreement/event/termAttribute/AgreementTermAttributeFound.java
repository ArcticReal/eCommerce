package com.skytala.eCommerce.domain.party.relations.agreement.event.termAttribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.termAttribute.AgreementTermAttribute;
public class AgreementTermAttributeFound implements Event{

	private List<AgreementTermAttribute> agreementTermAttributes;

	public AgreementTermAttributeFound(List<AgreementTermAttribute> agreementTermAttributes) {
		this.agreementTermAttributes = agreementTermAttributes;
	}

	public List<AgreementTermAttribute> getAgreementTermAttributes()	{
		return agreementTermAttributes;
	}

}
