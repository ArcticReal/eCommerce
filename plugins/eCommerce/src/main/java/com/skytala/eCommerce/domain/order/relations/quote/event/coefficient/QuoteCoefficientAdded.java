package com.skytala.eCommerce.domain.order.relations.quote.event.coefficient;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.coefficient.QuoteCoefficient;
public class QuoteCoefficientAdded implements Event{

	private QuoteCoefficient addedQuoteCoefficient;
	private boolean success;

	public QuoteCoefficientAdded(QuoteCoefficient addedQuoteCoefficient, boolean success){
		this.addedQuoteCoefficient = addedQuoteCoefficient;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteCoefficient getAddedQuoteCoefficient() {
		return addedQuoteCoefficient;
	}

}
