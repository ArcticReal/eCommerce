package com.skytala.eCommerce.domain.party.relations.agreementItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItemType.model.AgreementItemType;
public class AgreementItemTypeUpdated implements Event{

	private boolean success;

	public AgreementItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
