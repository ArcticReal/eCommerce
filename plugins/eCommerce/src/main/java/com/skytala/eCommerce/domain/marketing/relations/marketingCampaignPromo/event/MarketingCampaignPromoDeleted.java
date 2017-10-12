package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.model.MarketingCampaignPromo;
public class MarketingCampaignPromoDeleted implements Event{

	private boolean success;

	public MarketingCampaignPromoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
