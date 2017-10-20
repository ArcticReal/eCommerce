package com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.termAttribute.QuoteTermAttribute;
public class QuoteTermAttributeUpdated implements Event{

	private boolean success;

	public QuoteTermAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
