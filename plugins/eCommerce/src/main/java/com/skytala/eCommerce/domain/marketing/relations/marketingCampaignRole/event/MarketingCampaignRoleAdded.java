package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.model.MarketingCampaignRole;
public class MarketingCampaignRoleAdded implements Event{

	private MarketingCampaignRole addedMarketingCampaignRole;
	private boolean success;

	public MarketingCampaignRoleAdded(MarketingCampaignRole addedMarketingCampaignRole, boolean success){
		this.addedMarketingCampaignRole = addedMarketingCampaignRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MarketingCampaignRole getAddedMarketingCampaignRole() {
		return addedMarketingCampaignRole;
	}

}
