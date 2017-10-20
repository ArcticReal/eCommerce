package com.skytala.eCommerce.domain.party.relations.agreement.event.termAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.termAttribute.AgreementTermAttribute;
public class AgreementTermAttributeUpdated implements Event{

	private boolean success;

	public AgreementTermAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
