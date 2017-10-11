package com.skytala.eCommerce.domain.order.relations.quoteAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteAdjustment.model.QuoteAdjustment;
public class QuoteAdjustmentAdded implements Event{

	private QuoteAdjustment addedQuoteAdjustment;
	private boolean success;

	public QuoteAdjustmentAdded(QuoteAdjustment addedQuoteAdjustment, boolean success){
		this.addedQuoteAdjustment = addedQuoteAdjustment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteAdjustment getAddedQuoteAdjustment() {
		return addedQuoteAdjustment;
	}

}
