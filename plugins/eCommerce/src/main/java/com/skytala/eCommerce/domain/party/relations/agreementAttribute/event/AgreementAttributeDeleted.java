package com.skytala.eCommerce.domain.party.relations.agreementAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementAttribute.model.AgreementAttribute;
public class AgreementAttributeDeleted implements Event{

	private boolean success;

	public AgreementAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
