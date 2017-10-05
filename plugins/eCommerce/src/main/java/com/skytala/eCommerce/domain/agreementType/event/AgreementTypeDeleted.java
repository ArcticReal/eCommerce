package com.skytala.eCommerce.domain.agreementType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreementType.model.AgreementType;
public class AgreementTypeDeleted implements Event{

	private boolean success;

	public AgreementTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
