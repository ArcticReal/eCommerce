package com.skytala.eCommerce.domain.order.relations.quote.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.typeAttr.QuoteTypeAttr;
public class QuoteTypeAttrFound implements Event{

	private List<QuoteTypeAttr> quoteTypeAttrs;

	public QuoteTypeAttrFound(List<QuoteTypeAttr> quoteTypeAttrs) {
		this.quoteTypeAttrs = quoteTypeAttrs;
	}

	public List<QuoteTypeAttr> getQuoteTypeAttrs()	{
		return quoteTypeAttrs;
	}

}
