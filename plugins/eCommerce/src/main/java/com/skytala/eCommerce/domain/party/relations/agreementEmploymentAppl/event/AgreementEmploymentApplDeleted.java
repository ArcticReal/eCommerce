package com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.model.AgreementEmploymentAppl;
public class AgreementEmploymentApplDeleted implements Event{

	private boolean success;

	public AgreementEmploymentApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
