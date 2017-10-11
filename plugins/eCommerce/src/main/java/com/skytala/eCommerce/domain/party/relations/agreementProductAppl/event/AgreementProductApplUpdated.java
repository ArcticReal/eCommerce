package com.skytala.eCommerce.domain.party.relations.agreementProductAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.model.AgreementProductAppl;
public class AgreementProductApplUpdated implements Event{

	private boolean success;

	public AgreementProductApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
