package com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.model.AgreementGeographicalApplic;
public class AgreementGeographicalApplicDeleted implements Event{

	private boolean success;

	public AgreementGeographicalApplicDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
