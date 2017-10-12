package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.model.MarketingCampaignPromo;
public class MarketingCampaignPromoFound implements Event{

	private List<MarketingCampaignPromo> marketingCampaignPromos;

	public MarketingCampaignPromoFound(List<MarketingCampaignPromo> marketingCampaignPromos) {
		this.marketingCampaignPromos = marketingCampaignPromos;
	}

	public List<MarketingCampaignPromo> getMarketingCampaignPromos()	{
		return marketingCampaignPromos;
	}

}
