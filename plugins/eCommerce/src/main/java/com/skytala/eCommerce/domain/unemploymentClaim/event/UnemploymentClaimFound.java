package com.skytala.eCommerce.domain.unemploymentClaim.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.unemploymentClaim.model.UnemploymentClaim;
public class UnemploymentClaimFound implements Event{

	private List<UnemploymentClaim> unemploymentClaims;

	public UnemploymentClaimFound(List<UnemploymentClaim> unemploymentClaims) {
		this.unemploymentClaims = unemploymentClaims;
	}

	public List<UnemploymentClaim> getUnemploymentClaims()	{
		return unemploymentClaims;
	}

}
