package com.skytala.eCommerce.domain.party.relations.agreement.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.attribute.AgreementAttribute;
public class AgreementAttributeUpdated implements Event{

	private boolean success;

	public AgreementAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
