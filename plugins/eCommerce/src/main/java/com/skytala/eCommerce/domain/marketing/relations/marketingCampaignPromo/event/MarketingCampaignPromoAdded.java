package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.model.MarketingCampaignPromo;
public class MarketingCampaignPromoAdded implements Event{

	private MarketingCampaignPromo addedMarketingCampaignPromo;
	private boolean success;

	public MarketingCampaignPromoAdded(MarketingCampaignPromo addedMarketingCampaignPromo, boolean success){
		this.addedMarketingCampaignPromo = addedMarketingCampaignPromo;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MarketingCampaignPromo getAddedMarketingCampaignPromo() {
		return addedMarketingCampaignPromo;
	}

}
