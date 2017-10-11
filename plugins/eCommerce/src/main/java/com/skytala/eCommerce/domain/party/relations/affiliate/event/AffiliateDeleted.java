package com.skytala.eCommerce.domain.party.relations.affiliate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.affiliate.model.Affiliate;
public class AffiliateDeleted implements Event{

	private boolean success;

	public AffiliateDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}