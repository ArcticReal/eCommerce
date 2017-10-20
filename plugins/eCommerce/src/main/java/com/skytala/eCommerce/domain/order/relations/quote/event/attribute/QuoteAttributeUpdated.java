package com.skytala.eCommerce.domain.order.relations.quote.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.attribute.QuoteAttribute;
public class QuoteAttributeUpdated implements Event{

	private boolean success;

	public QuoteAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
