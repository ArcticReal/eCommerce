package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.promo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.promo.MarketingCampaignPromo;
public class MarketingCampaignPromoDeleted implements Event{

	private boolean success;

	public MarketingCampaignPromoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
