package com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.model.QuoteTypeAttr;
public class QuoteTypeAttrUpdated implements Event{

	private boolean success;

	public QuoteTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}