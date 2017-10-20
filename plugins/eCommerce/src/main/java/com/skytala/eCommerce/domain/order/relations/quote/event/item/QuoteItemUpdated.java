package com.skytala.eCommerce.domain.order.relations.quote.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.item.QuoteItem;
public class QuoteItemUpdated implements Event{

	private boolean success;

	public QuoteItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
