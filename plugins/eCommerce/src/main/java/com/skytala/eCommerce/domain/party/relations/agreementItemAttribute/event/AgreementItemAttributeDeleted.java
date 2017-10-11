package com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.model.AgreementItemAttribute;
public class AgreementItemAttributeDeleted implements Event{

	private boolean success;

	public AgreementItemAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
