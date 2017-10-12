package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.model.MarketingCampaignPrice;
public class MarketingCampaignPriceFound implements Event{

	private List<MarketingCampaignPrice> marketingCampaignPrices;

	public MarketingCampaignPriceFound(List<MarketingCampaignPrice> marketingCampaignPrices) {
		this.marketingCampaignPrices = marketingCampaignPrices;
	}

	public List<MarketingCampaignPrice> getMarketingCampaignPrices()	{
		return marketingCampaignPrices;
	}

}
