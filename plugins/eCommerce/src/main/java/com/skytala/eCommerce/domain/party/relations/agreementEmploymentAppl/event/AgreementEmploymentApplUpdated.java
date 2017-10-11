package com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.model.AgreementEmploymentAppl;
public class AgreementEmploymentApplUpdated implements Event{

	private boolean success;

	public AgreementEmploymentApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
