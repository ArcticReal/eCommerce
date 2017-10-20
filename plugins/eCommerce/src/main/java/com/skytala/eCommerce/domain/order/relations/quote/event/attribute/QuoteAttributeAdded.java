package com.skytala.eCommerce.domain.order.relations.quote.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.attribute.QuoteAttribute;
public class QuoteAttributeAdded implements Event{

	private QuoteAttribute addedQuoteAttribute;
	private boolean success;

	public QuoteAttributeAdded(QuoteAttribute addedQuoteAttribute, boolean success){
		this.addedQuoteAttribute = addedQuoteAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteAttribute getAddedQuoteAttribute() {
		return addedQuoteAttribute;
	}

}
