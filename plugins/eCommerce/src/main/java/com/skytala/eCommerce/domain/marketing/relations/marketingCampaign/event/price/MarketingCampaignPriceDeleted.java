package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.price;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.price.MarketingCampaignPrice;
public class MarketingCampaignPriceDeleted implements Event{

	private boolean success;

	public MarketingCampaignPriceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}