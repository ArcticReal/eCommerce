package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.promo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.promo.MarketingCampaignPromo;
public class MarketingCampaignPromoUpdated implements Event{

	private boolean success;

	public MarketingCampaignPromoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
