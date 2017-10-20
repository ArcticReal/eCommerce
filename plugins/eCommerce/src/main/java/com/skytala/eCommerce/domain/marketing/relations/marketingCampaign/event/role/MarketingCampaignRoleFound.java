package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.role.MarketingCampaignRole;
public class MarketingCampaignRoleFound implements Event{

	private List<MarketingCampaignRole> marketingCampaignRoles;

	public MarketingCampaignRoleFound(List<MarketingCampaignRole> marketingCampaignRoles) {
		this.marketingCampaignRoles = marketingCampaignRoles;
	}

	public List<MarketingCampaignRole> getMarketingCampaignRoles()	{
		return marketingCampaignRoles;
	}

}
