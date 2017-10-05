package com.skytala.eCommerce.domain.agreementItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreementItemType.model.AgreementItemType;
public class AgreementItemTypeDeleted implements Event{

	private boolean success;

	public AgreementItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
