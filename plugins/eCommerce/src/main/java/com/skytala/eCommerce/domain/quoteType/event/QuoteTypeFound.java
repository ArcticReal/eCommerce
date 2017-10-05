package com.skytala.eCommerce.domain.quoteType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quoteType.model.QuoteType;
public class QuoteTypeFound implements Event{

	private List<QuoteType> quoteTypes;

	public QuoteTypeFound(List<QuoteType> quoteTypes) {
		this.quoteTypes = quoteTypes;
	}

	public List<QuoteType> getQuoteTypes()	{
		return quoteTypes;
	}

}
