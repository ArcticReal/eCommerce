package com.skytala.eCommerce.domain.agreementContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreementContentType.model.AgreementContentType;
public class AgreementContentTypeDeleted implements Event{

	private boolean success;

	public AgreementContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
