package com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.model.QuoteWorkEffort;
public class QuoteWorkEffortAdded implements Event{

	private QuoteWorkEffort addedQuoteWorkEffort;
	private boolean success;

	public QuoteWorkEffortAdded(QuoteWorkEffort addedQuoteWorkEffort, boolean success){
		this.addedQuoteWorkEffort = addedQuoteWorkEffort;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteWorkEffort getAddedQuoteWorkEffort() {
		return addedQuoteWorkEffort;
	}

}