package com.skytala.eCommerce.domain.party.relations.agreement.event.contentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.contentType.AgreementContentType;
public class AgreementContentTypeDeleted implements Event{

	private boolean success;

	public AgreementContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
