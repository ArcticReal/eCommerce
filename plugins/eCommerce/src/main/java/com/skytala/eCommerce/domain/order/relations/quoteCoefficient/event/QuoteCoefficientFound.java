package com.skytala.eCommerce.domain.order.relations.quoteCoefficient.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteCoefficient.model.QuoteCoefficient;
public class QuoteCoefficientFound implements Event{

	private List<QuoteCoefficient> quoteCoefficients;

	public QuoteCoefficientFound(List<QuoteCoefficient> quoteCoefficients) {
		this.quoteCoefficients = quoteCoefficients;
	}

	public List<QuoteCoefficient> getQuoteCoefficients()	{
		return quoteCoefficients;
	}

}
