package com.skytala.eCommerce.domain.agreement.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreement.model.Agreement;
public class AgreementDeleted implements Event{

	private boolean success;

	public AgreementDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
