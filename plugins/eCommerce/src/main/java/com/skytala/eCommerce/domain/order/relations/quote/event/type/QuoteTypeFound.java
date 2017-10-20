package com.skytala.eCommerce.domain.order.relations.quote.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.type.QuoteType;
public class QuoteTypeFound implements Event{

	private List<QuoteType> quoteTypes;

	public QuoteTypeFound(List<QuoteType> quoteTypes) {
		this.quoteTypes = quoteTypes;
	}

	public List<QuoteType> getQuoteTypes()	{
		return quoteTypes;
	}

}
