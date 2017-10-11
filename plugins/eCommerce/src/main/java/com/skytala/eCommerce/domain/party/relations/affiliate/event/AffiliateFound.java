package com.skytala.eCommerce.domain.party.relations.affiliate.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.affiliate.model.Affiliate;
public class AffiliateFound implements Event{

	private List<Affiliate> affiliates;

	public AffiliateFound(List<Affiliate> affiliates) {
		this.affiliates = affiliates;
	}

	public List<Affiliate> getAffiliates()	{
		return affiliates;
	}

}
