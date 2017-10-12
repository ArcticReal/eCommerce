package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.model.MarketingCampaignRole;
public class MarketingCampaignRoleDeleted implements Event{

	private boolean success;

	public MarketingCampaignRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
