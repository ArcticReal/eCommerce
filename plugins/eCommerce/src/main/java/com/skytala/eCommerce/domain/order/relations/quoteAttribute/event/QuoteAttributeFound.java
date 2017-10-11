package com.skytala.eCommerce.domain.order.relations.quoteAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteAttribute.model.QuoteAttribute;
public class QuoteAttributeFound implements Event{

	private List<QuoteAttribute> quoteAttributes;

	public QuoteAttributeFound(List<QuoteAttribute> quoteAttributes) {
		this.quoteAttributes = quoteAttributes;
	}

	public List<QuoteAttribute> getQuoteAttributes()	{
		return quoteAttributes;
	}

}
