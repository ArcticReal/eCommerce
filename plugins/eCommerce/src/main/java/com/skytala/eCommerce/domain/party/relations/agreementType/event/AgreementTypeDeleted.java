package com.skytala.eCommerce.domain.party.relations.agreementType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementType.model.AgreementType;
public class AgreementTypeDeleted implements Event{

	private boolean success;

	public AgreementTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
