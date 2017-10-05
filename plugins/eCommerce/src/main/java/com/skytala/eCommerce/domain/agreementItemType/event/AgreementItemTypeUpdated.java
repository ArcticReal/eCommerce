package com.skytala.eCommerce.domain.agreementItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreementItemType.model.AgreementItemType;
public class AgreementItemTypeUpdated implements Event{

	private boolean success;

	public AgreementItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
