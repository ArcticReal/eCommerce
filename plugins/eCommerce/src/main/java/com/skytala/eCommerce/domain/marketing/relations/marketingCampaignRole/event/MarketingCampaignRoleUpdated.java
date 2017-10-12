package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.model.MarketingCampaignRole;
public class MarketingCampaignRoleUpdated implements Event{

	private boolean success;

	public MarketingCampaignRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
