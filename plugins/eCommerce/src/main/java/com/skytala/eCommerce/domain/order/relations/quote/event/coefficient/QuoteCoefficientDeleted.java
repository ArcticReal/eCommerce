package com.skytala.eCommerce.domain.order.relations.quote.event.coefficient;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.coefficient.QuoteCoefficient;
public class QuoteCoefficientDeleted implements Event{

	private boolean success;

	public QuoteCoefficientDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
