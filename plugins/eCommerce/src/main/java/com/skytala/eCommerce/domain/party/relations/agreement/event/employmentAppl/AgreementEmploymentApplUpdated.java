package com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.employmentAppl.AgreementEmploymentAppl;
public class AgreementEmploymentApplUpdated implements Event{

	private boolean success;

	public AgreementEmploymentApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
