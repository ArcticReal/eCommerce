package com.skytala.eCommerce.domain.order.relations.quote.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.attribute.QuoteAttribute;
public class QuoteAttributeFound implements Event{

	private List<QuoteAttribute> quoteAttributes;

	public QuoteAttributeFound(List<QuoteAttribute> quoteAttributes) {
		this.quoteAttributes = quoteAttributes;
	}

	public List<QuoteAttribute> getQuoteAttributes()	{
		return quoteAttributes;
	}

}
