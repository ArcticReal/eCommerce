package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.quote;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.quote.SalesOpportunityQuote;
public class SalesOpportunityQuoteFound implements Event{

	private List<SalesOpportunityQuote> salesOpportunityQuotes;

	public SalesOpportunityQuoteFound(List<SalesOpportunityQuote> salesOpportunityQuotes) {
		this.salesOpportunityQuotes = salesOpportunityQuotes;
	}

	public List<SalesOpportunityQuote> getSalesOpportunityQuotes()	{
		return salesOpportunityQuotes;
	}

}
