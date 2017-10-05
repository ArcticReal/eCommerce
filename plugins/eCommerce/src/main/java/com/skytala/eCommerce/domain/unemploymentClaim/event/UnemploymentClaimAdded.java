package com.skytala.eCommerce.domain.unemploymentClaim.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.unemploymentClaim.model.UnemploymentClaim;
public class UnemploymentClaimAdded implements Event{

	private UnemploymentClaim addedUnemploymentClaim;
	private boolean success;

	public UnemploymentClaimAdded(UnemploymentClaim addedUnemploymentClaim, boolean success){
		this.addedUnemploymentClaim = addedUnemploymentClaim;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public UnemploymentClaim getAddedUnemploymentClaim() {
		return addedUnemploymentClaim;
	}

}
