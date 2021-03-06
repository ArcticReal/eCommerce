package com.skytala.eCommerce.domain.order.relations.quote.event.adjustment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.adjustment.QuoteAdjustment;
public class QuoteAdjustmentFound implements Event{

	private List<QuoteAdjustment> quoteAdjustments;

	public QuoteAdjustmentFound(List<QuoteAdjustment> quoteAdjustments) {
		this.quoteAdjustments = quoteAdjustments;
	}

	public List<QuoteAdjustment> getQuoteAdjustments()	{
		return quoteAdjustments;
	}

}
