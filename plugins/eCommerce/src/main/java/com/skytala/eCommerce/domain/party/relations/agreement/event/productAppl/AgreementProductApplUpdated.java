package com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.productAppl.AgreementProductAppl;
public class AgreementProductApplUpdated implements Event{

	private boolean success;

	public AgreementProductApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
