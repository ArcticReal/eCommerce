package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.quote;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.quote.SalesOpportunityQuote;
public class SalesOpportunityQuoteDeleted implements Event{

	private boolean success;

	public SalesOpportunityQuoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
