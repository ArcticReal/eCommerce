package com.skytala.eCommerce.domain.order.relations.quote.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.type.QuoteType;
public class QuoteTypeAdded implements Event{

	private QuoteType addedQuoteType;
	private boolean success;

	public QuoteTypeAdded(QuoteType addedQuoteType, boolean success){
		this.addedQuoteType = addedQuoteType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteType getAddedQuoteType() {
		return addedQuoteType;
	}

}
