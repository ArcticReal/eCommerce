package com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.geographicalApplic.AgreementGeographicalApplic;
public class AgreementGeographicalApplicUpdated implements Event{

	private boolean success;

	public AgreementGeographicalApplicUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
