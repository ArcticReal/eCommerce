package com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.productAppl.AgreementProductAppl;
public class AgreementProductApplDeleted implements Event{

	private boolean success;

	public AgreementProductApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
