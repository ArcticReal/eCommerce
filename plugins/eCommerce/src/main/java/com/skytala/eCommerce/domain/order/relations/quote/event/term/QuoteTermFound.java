package com.skytala.eCommerce.domain.order.relations.quote.event.term;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.term.QuoteTerm;
public class QuoteTermFound implements Event{

	private List<QuoteTerm> quoteTerms;

	public QuoteTermFound(List<QuoteTerm> quoteTerms) {
		this.quoteTerms = quoteTerms;
	}

	public List<QuoteTerm> getQuoteTerms()	{
		return quoteTerms;
	}

}
