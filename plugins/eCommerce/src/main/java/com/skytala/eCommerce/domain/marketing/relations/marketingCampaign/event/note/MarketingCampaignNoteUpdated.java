package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.note.MarketingCampaignNote;
public class MarketingCampaignNoteUpdated implements Event{

	private boolean success;

	public MarketingCampaignNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
