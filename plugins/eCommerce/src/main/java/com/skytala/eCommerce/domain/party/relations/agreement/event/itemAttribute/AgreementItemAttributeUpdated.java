package com.skytala.eCommerce.domain.party.relations.agreement.event.itemAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.itemAttribute.AgreementItemAttribute;
public class AgreementItemAttributeUpdated implements Event{

	private boolean success;

	public AgreementItemAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
