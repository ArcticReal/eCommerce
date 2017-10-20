package com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.termAttribute.QuoteTermAttribute;
public class QuoteTermAttributeFound implements Event{

	private List<QuoteTermAttribute> quoteTermAttributes;

	public QuoteTermAttributeFound(List<QuoteTermAttribute> quoteTermAttributes) {
		this.quoteTermAttributes = quoteTermAttributes;
	}

	public List<QuoteTermAttribute> getQuoteTermAttributes()	{
		return quoteTermAttributes;
	}

}
