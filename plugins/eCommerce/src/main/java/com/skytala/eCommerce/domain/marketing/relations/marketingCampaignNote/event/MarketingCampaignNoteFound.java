package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.model.MarketingCampaignNote;
public class MarketingCampaignNoteFound implements Event{

	private List<MarketingCampaignNote> marketingCampaignNotes;

	public MarketingCampaignNoteFound(List<MarketingCampaignNote> marketingCampaignNotes) {
		this.marketingCampaignNotes = marketingCampaignNotes;
	}

	public List<MarketingCampaignNote> getMarketingCampaignNotes()	{
		return marketingCampaignNotes;
	}

}
