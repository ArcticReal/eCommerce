package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.quote;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.quote.SalesOpportunityQuote;
public class SalesOpportunityQuoteAdded implements Event{

	private SalesOpportunityQuote addedSalesOpportunityQuote;
	private boolean success;

	public SalesOpportunityQuoteAdded(SalesOpportunityQuote addedSalesOpportunityQuote, boolean success){
		this.addedSalesOpportunityQuote = addedSalesOpportunityQuote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesOpportunityQuote getAddedSalesOpportunityQuote() {
		return addedSalesOpportunityQuote;
	}

}
