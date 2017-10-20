package com.skytala.eCommerce.domain.order.relations.quote.event.coefficient;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.coefficient.QuoteCoefficient;
public class QuoteCoefficientFound implements Event{

	private List<QuoteCoefficient> quoteCoefficients;

	public QuoteCoefficientFound(List<QuoteCoefficient> quoteCoefficients) {
		this.quoteCoefficients = quoteCoefficients;
	}

	public List<QuoteCoefficient> getQuoteCoefficients()	{
		return quoteCoefficients;
	}

}
