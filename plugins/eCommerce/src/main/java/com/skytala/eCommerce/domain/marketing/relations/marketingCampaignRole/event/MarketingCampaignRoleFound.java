package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.model.MarketingCampaignRole;
public class MarketingCampaignRoleFound implements Event{

	private List<MarketingCampaignRole> marketingCampaignRoles;

	public MarketingCampaignRoleFound(List<MarketingCampaignRole> marketingCampaignRoles) {
		this.marketingCampaignRoles = marketingCampaignRoles;
	}

	public List<MarketingCampaignRole> getMarketingCampaignRoles()	{
		return marketingCampaignRoles;
	}

}
