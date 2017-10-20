package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.quote;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.quote.SalesOpportunityQuote;
public class SalesOpportunityQuoteUpdated implements Event{

	private boolean success;

	public SalesOpportunityQuoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
