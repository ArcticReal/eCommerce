package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.MarketingCampaign;
public class MarketingCampaignFound implements Event{

	private List<MarketingCampaign> marketingCampaigns;

	public MarketingCampaignFound(List<MarketingCampaign> marketingCampaigns) {
		this.marketingCampaigns = marketingCampaigns;
	}

	public List<MarketingCampaign> getMarketingCampaigns()	{
		return marketingCampaigns;
	}

}
