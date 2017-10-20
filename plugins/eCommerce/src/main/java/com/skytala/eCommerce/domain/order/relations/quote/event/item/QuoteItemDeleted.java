package com.skytala.eCommerce.domain.order.relations.quote.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.item.QuoteItem;
public class QuoteItemDeleted implements Event{

	private boolean success;

	public QuoteItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
