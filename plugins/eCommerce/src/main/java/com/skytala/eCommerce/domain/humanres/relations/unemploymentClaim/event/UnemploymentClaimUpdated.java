package com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.model.UnemploymentClaim;
public class UnemploymentClaimUpdated implements Event{

	private boolean success;

	public UnemploymentClaimUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
