package com.skytala.eCommerce.domain.party.relations.agreementTermAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementTermAttribute.model.AgreementTermAttribute;
public class AgreementTermAttributeDeleted implements Event{

	private boolean success;

	public AgreementTermAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
