package com.skytala.eCommerce.domain.order.relations.quoteTerm.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteTerm.model.QuoteTerm;
public class QuoteTermFound implements Event{

	private List<QuoteTerm> quoteTerms;

	public QuoteTermFound(List<QuoteTerm> quoteTerms) {
		this.quoteTerms = quoteTerms;
	}

	public List<QuoteTerm> getQuoteTerms()	{
		return quoteTerms;
	}

}
