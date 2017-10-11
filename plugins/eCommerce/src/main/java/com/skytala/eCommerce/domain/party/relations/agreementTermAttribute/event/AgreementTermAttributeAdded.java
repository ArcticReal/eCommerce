package com.skytala.eCommerce.domain.party.relations.agreementTermAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementTermAttribute.model.AgreementTermAttribute;
public class AgreementTermAttributeAdded implements Event{

	private AgreementTermAttribute addedAgreementTermAttribute;
	private boolean success;

	public AgreementTermAttributeAdded(AgreementTermAttribute addedAgreementTermAttribute, boolean success){
		this.addedAgreementTermAttribute = addedAgreementTermAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementTermAttribute getAddedAgreementTermAttribute() {
		return addedAgreementTermAttribute;
	}

}
