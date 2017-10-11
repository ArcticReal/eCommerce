package com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.model.AgreementFacilityAppl;
public class AgreementFacilityApplUpdated implements Event{

	private boolean success;

	public AgreementFacilityApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
