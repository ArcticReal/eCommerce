package com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.workEffortApplic.AgreementWorkEffortApplic;
public class AgreementWorkEffortApplicDeleted implements Event{

	private boolean success;

	public AgreementWorkEffortApplicDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
