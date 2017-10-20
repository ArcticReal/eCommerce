package com.skytala.eCommerce.domain.order.relations.quote.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.typeAttr.QuoteTypeAttr;
public class QuoteTypeAttrAdded implements Event{

	private QuoteTypeAttr addedQuoteTypeAttr;
	private boolean success;

	public QuoteTypeAttrAdded(QuoteTypeAttr addedQuoteTypeAttr, boolean success){
		this.addedQuoteTypeAttr = addedQuoteTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteTypeAttr getAddedQuoteTypeAttr() {
		return addedQuoteTypeAttr;
	}

}
