package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.price;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.price.MarketingCampaignPrice;
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
