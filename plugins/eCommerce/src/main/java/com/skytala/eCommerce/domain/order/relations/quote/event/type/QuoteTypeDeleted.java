package com.skytala.eCommerce.domain.order.relations.quote.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.type.QuoteType;
public class QuoteTypeDeleted implements Event{

	private boolean success;

	public QuoteTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
