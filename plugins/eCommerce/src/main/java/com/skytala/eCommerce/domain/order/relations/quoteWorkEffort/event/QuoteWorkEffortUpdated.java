package com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.model.QuoteWorkEffort;
public class QuoteWorkEffortUpdated implements Event{

	private boolean success;

	public QuoteWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
