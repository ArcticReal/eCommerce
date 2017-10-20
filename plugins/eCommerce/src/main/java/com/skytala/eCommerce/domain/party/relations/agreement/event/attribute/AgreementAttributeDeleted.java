package com.skytala.eCommerce.domain.party.relations.agreement.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.attribute.AgreementAttribute;
public class AgreementAttributeDeleted implements Event{

	private boolean success;

	public AgreementAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
