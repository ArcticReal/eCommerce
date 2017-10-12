package com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.model.UnemploymentClaim;
public class UnemploymentClaimDeleted implements Event{

	private boolean success;

	public UnemploymentClaimDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
