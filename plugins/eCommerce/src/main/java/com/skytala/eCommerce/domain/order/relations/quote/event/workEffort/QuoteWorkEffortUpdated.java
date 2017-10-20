package com.skytala.eCommerce.domain.order.relations.quote.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.workEffort.QuoteWorkEffort;
public class QuoteWorkEffortUpdated implements Event{

	private boolean success;

	public QuoteWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
