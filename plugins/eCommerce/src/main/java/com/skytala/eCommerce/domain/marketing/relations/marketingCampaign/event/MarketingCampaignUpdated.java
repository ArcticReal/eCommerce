package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.MarketingCampaign;
public class MarketingCampaignUpdated implements Event{

	private boolean success;

	public MarketingCampaignUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
