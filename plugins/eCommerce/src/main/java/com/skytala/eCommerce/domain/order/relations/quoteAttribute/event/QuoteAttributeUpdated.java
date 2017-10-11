package com.skytala.eCommerce.domain.order.relations.quoteAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteAttribute.model.QuoteAttribute;
public class QuoteAttributeUpdated implements Event{

	private boolean success;

	public QuoteAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
