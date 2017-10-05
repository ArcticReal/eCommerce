package com.skytala.eCommerce.domain.quoteAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quoteAdjustment.model.QuoteAdjustment;
public class QuoteAdjustmentUpdated implements Event{

	private boolean success;

	public QuoteAdjustmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
