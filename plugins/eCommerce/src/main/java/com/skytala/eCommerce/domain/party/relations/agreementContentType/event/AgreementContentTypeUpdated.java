package com.skytala.eCommerce.domain.party.relations.agreementContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementContentType.model.AgreementContentType;
public class AgreementContentTypeUpdated implements Event{

	private boolean success;

	public AgreementContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
