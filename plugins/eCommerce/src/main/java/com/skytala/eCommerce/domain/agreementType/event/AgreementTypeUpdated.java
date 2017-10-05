package com.skytala.eCommerce.domain.agreementType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreementType.model.AgreementType;
public class AgreementTypeUpdated implements Event{

	private boolean success;

	public AgreementTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
