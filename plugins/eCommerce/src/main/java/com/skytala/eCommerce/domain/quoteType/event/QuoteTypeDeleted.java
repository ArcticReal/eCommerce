package com.skytala.eCommerce.domain.quoteType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quoteType.model.QuoteType;
public class QuoteTypeDeleted implements Event{

	private boolean success;

	public QuoteTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
