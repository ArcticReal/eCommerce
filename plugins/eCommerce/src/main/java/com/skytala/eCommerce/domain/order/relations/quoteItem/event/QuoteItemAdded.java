package com.skytala.eCommerce.domain.order.relations.quoteItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteItem.model.QuoteItem;
public class QuoteItemAdded implements Event{

	private QuoteItem addedQuoteItem;
	private boolean success;

	public QuoteItemAdded(QuoteItem addedQuoteItem, boolean success){
		this.addedQuoteItem = addedQuoteItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteItem getAddedQuoteItem() {
		return addedQuoteItem;
	}

}
