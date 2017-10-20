package com.skytala.eCommerce.domain.order.relations.quote.event.term;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.term.QuoteTerm;
public class QuoteTermUpdated implements Event{

	private boolean success;

	public QuoteTermUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
