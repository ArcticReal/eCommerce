package com.skytala.eCommerce.domain.party.relations.agreementItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItem.model.AgreementItem;
public class AgreementItemDeleted implements Event{

	private boolean success;

	public AgreementItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
