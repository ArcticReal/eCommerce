package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.model.SalesOpportunityQuote;
public class SalesOpportunityQuoteDeleted implements Event{

	private boolean success;

	public SalesOpportunityQuoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
