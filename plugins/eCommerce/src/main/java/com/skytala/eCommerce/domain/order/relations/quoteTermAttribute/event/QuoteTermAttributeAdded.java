package com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.model.QuoteTermAttribute;
public class QuoteTermAttributeAdded implements Event{

	private QuoteTermAttribute addedQuoteTermAttribute;
	private boolean success;

	public QuoteTermAttributeAdded(QuoteTermAttribute addedQuoteTermAttribute, boolean success){
		this.addedQuoteTermAttribute = addedQuoteTermAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteTermAttribute getAddedQuoteTermAttribute() {
		return addedQuoteTermAttribute;
	}

}
