package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.model.MarketingCampaignNote;
public class MarketingCampaignNoteUpdated implements Event{

	private boolean success;

	public MarketingCampaignNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
