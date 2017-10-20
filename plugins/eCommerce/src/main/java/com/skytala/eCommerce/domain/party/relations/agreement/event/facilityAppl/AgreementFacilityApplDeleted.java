package com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.facilityAppl.AgreementFacilityAppl;
public class AgreementFacilityApplDeleted implements Event{

	private boolean success;

	public AgreementFacilityApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
