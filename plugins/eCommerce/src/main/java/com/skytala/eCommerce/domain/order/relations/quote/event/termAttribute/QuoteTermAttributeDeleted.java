package com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.termAttribute.QuoteTermAttribute;
public class QuoteTermAttributeDeleted implements Event{

	private boolean success;

	public QuoteTermAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
