package com.skytala.eCommerce.domain.agreement.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreement.model.Agreement;
public class AgreementUpdated implements Event{

	private boolean success;

	public AgreementUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
