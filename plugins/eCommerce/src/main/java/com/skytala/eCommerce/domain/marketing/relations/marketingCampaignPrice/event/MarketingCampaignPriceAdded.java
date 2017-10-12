package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.model.MarketingCampaignPrice;
public class MarketingCampaignPriceAdded implements Event{

	private MarketingCampaignPrice addedMarketingCampaignPrice;
	private boolean success;

	public MarketingCampaignPriceAdded(MarketingCampaignPrice addedMarketingCampaignPrice, boolean success){
		this.addedMarketingCampaignPrice = addedMarketingCampaignPrice;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MarketingCampaignPrice getAddedMarketingCampaignPrice() {
		return addedMarketingCampaignPrice;
	}

}
