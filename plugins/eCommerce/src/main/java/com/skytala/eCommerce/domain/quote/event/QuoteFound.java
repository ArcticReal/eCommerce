package com.skytala.eCommerce.domain.quote.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quote.model.Quote;
public class QuoteFound implements Event{

	private List<Quote> quotes;

	public QuoteFound(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public List<Quote> getQuotes()	{
		return quotes;
	}

}
