package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.model.SalesOpportunityQuote;
public class SalesOpportunityQuoteFound implements Event{

	private List<SalesOpportunityQuote> salesOpportunityQuotes;

	public SalesOpportunityQuoteFound(List<SalesOpportunityQuote> salesOpportunityQuotes) {
		this.salesOpportunityQuotes = salesOpportunityQuotes;
	}

	public List<SalesOpportunityQuote> getSalesOpportunityQuotes()	{
		return salesOpportunityQuotes;
	}

}
