package com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.model.QuoteTermAttribute;
public class QuoteTermAttributeDeleted implements Event{

	private boolean success;

	public QuoteTermAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
