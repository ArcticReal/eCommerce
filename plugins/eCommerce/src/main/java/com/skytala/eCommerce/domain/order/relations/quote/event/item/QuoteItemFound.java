package com.skytala.eCommerce.domain.order.relations.quote.event.item;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.item.QuoteItem;
public class QuoteItemFound implements Event{

	private List<QuoteItem> quoteItems;

	public QuoteItemFound(List<QuoteItem> quoteItems) {
		this.quoteItems = quoteItems;
	}

	public List<QuoteItem> getQuoteItems()	{
		return quoteItems;
	}

}
