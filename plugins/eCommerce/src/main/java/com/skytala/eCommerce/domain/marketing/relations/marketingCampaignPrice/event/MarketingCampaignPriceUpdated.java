package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.model.MarketingCampaignPrice;
public class MarketingCampaignPriceUpdated implements Event{

	private boolean success;

	public MarketingCampaignPriceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
