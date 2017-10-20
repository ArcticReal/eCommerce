package com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.employmentAppl.AgreementEmploymentAppl;
public class AgreementEmploymentApplDeleted implements Event{

	private boolean success;

	public AgreementEmploymentApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
