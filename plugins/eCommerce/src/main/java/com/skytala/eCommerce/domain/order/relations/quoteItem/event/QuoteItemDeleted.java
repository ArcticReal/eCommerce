package com.skytala.eCommerce.domain.order.relations.quoteItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteItem.model.QuoteItem;
public class QuoteItemDeleted implements Event{

	private boolean success;

	public QuoteItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
