package com.skytala.eCommerce.domain.marketingCampaign.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketingCampaign.model.MarketingCampaign;
public class MarketingCampaignDeleted implements Event{

	private boolean success;

	public MarketingCampaignDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
