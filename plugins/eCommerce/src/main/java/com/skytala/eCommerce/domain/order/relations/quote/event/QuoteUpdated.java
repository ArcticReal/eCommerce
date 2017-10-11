package com.skytala.eCommerce.domain.order.relations.quote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.Quote;
public class QuoteUpdated implements Event{

	private boolean success;

	public QuoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
