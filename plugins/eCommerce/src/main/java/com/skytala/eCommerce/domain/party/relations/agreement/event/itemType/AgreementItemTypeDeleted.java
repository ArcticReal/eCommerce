package com.skytala.eCommerce.domain.party.relations.agreement.event.itemType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.itemType.AgreementItemType;
public class AgreementItemTypeDeleted implements Event{

	private boolean success;

	public AgreementItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
