package com.skytala.eCommerce.domain.marketingCampaign.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketingCampaign.model.MarketingCampaign;
public class MarketingCampaignAdded implements Event{

	private MarketingCampaign addedMarketingCampaign;
	private boolean success;

	public MarketingCampaignAdded(MarketingCampaign addedMarketingCampaign, boolean success){
		this.addedMarketingCampaign = addedMarketingCampaign;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MarketingCampaign getAddedMarketingCampaign() {
		return addedMarketingCampaign;
	}

}
