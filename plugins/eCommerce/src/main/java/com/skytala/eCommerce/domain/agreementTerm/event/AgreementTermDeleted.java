package com.skytala.eCommerce.domain.agreementTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreementTerm.model.AgreementTerm;
public class AgreementTermDeleted implements Event{

	private boolean success;

	public AgreementTermDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
