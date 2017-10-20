package com.skytala.eCommerce.domain.order.relations.quote.event.adjustment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.adjustment.QuoteAdjustment;
public class QuoteAdjustmentUpdated implements Event{

	private boolean success;

	public QuoteAdjustmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
