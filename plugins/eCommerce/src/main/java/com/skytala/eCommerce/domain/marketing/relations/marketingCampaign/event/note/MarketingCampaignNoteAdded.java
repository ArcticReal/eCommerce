package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.note.MarketingCampaignNote;
public class MarketingCampaignNoteAdded implements Event{

	private MarketingCampaignNote addedMarketingCampaignNote;
	private boolean success;

	public MarketingCampaignNoteAdded(MarketingCampaignNote addedMarketingCampaignNote, boolean success){
		this.addedMarketingCampaignNote = addedMarketingCampaignNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MarketingCampaignNote getAddedMarketingCampaignNote() {
		return addedMarketingCampaignNote;
	}

}
