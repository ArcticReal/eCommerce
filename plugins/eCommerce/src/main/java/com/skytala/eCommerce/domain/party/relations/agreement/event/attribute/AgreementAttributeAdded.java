package com.skytala.eCommerce.domain.party.relations.agreement.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.attribute.AgreementAttribute;
public class AgreementAttributeAdded implements Event{

	private AgreementAttribute addedAgreementAttribute;
	private boolean success;

	public AgreementAttributeAdded(AgreementAttribute addedAgreementAttribute, boolean success){
		this.addedAgreementAttribute = addedAgreementAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementAttribute getAddedAgreementAttribute() {
		return addedAgreementAttribute;
	}

}
