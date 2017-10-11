package com.skytala.eCommerce.domain.party.relations.agreementProductAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.model.AgreementProductAppl;
public class AgreementProductApplDeleted implements Event{

	private boolean success;

	public AgreementProductApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
