package com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.model.AgreementItemAttribute;
public class AgreementItemAttributeUpdated implements Event{

	private boolean success;

	public AgreementItemAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
