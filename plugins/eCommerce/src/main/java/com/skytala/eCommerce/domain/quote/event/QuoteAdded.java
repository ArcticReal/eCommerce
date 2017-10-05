package com.skytala.eCommerce.domain.quote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quote.model.Quote;
public class QuoteAdded implements Event{

	private Quote addedQuote;
	private boolean success;

	public QuoteAdded(Quote addedQuote, boolean success){
		this.addedQuote = addedQuote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Quote getAddedQuote() {
		return addedQuote;
	}

}
