package com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.model.QuoteTermAttribute;
public class QuoteTermAttributeFound implements Event{

	private List<QuoteTermAttribute> quoteTermAttributes;

	public QuoteTermAttributeFound(List<QuoteTermAttribute> quoteTermAttributes) {
		this.quoteTermAttributes = quoteTermAttributes;
	}

	public List<QuoteTermAttribute> getQuoteTermAttributes()	{
		return quoteTermAttributes;
	}

}
