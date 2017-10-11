package com.skytala.eCommerce.domain.order.relations.quoteCoefficient.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteCoefficient.model.QuoteCoefficient;
public class QuoteCoefficientDeleted implements Event{

	private boolean success;

	public QuoteCoefficientDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
