package com.skytala.eCommerce.domain.party.relations.affiliate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.affiliate.model.Affiliate;
public class AffiliateAdded implements Event{

	private Affiliate addedAffiliate;
	private boolean success;

	public AffiliateAdded(Affiliate addedAffiliate, boolean success){
		this.addedAffiliate = addedAffiliate;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Affiliate getAddedAffiliate() {
		return addedAffiliate;
	}

}
