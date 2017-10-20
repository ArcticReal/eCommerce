package com.skytala.eCommerce.domain.order.relations.quote.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.workEffort.QuoteWorkEffort;
public class QuoteWorkEffortDeleted implements Event{

	private boolean success;

	public QuoteWorkEffortDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
