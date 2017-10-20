package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.note.MarketingCampaignNote;
public class MarketingCampaignNoteDeleted implements Event{

	private boolean success;

	public MarketingCampaignNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
