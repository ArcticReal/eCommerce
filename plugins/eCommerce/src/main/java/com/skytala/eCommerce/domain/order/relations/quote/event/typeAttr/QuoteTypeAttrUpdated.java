package com.skytala.eCommerce.domain.order.relations.quote.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.typeAttr.QuoteTypeAttr;
public class QuoteTypeAttrUpdated implements Event{

	private boolean success;

	public QuoteTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
