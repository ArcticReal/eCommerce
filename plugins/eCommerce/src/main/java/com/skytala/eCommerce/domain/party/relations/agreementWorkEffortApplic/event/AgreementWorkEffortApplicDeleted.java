package com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.model.AgreementWorkEffortApplic;
public class AgreementWorkEffortApplicDeleted implements Event{

	private boolean success;

	public AgreementWorkEffortApplicDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
