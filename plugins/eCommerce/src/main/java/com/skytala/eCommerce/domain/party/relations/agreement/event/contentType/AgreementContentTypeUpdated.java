package com.skytala.eCommerce.domain.party.relations.agreement.event.contentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.contentType.AgreementContentType;
public class AgreementContentTypeUpdated implements Event{

	private boolean success;

	public AgreementContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
